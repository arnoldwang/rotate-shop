package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.utils.CatContext;
import com.dianping.rotate.shop.utils.Switch;

import java.util.List;

/**
 * Created by zaza on 15/1/20.
 */
public abstract class BatchMessageRunner extends AbstractMessageRunner {

    @Override
    public void run() {
        while(true){
            CatContext catContext = CatContext.transaction(TRANSACTION_NAME + "_" + getMessageSourceType());
            try {
                if(Switch.on()){
                    catContext.startTransactionWithStep("Load");
                    List<MessageEntity> messages = messageQueueDAO.getUnprocessedMessage(getMessageSourceType(),
                            getPOIMessageType(),
                            MAX_RETRY, PROCESS_MESSAGE_LIMIT);
                    if (messages.size() == 0) {
                        catContext.startNewStep("Sleep");
                        Thread.sleep(INTERVAL_WHEN_NO_TASK);
                    } else {
                        catContext.startNewStep("Run");
                        runMessages(messages);
                    }
                }
            }catch(Exception ex){
                logger.error(ex.getMessage(), ex);
            }finally {
                catContext.stopTransaction();
            }
        }
    }
}
