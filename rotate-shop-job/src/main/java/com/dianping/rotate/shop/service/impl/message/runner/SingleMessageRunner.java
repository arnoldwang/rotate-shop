package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.service.impl.message.runner.AbstractMessageRunner;
import com.dianping.rotate.shop.utils.CatContext;
import com.dianping.rotate.shop.utils.Switch;

import java.util.List;

/**
 * Created by zaza on 15/1/20.
 */
public abstract class SingleMessageRunner extends AbstractMessageRunner {

    private static final int MAX_RETRY = 10;
    private static final int PROCESS_MESSAGE_LIMIT = 1000;
    private static final int INTERVAL_WHEN_NO_TASK = 100;
    private static final String TRANSACTION_NAME = "Job_Batch";

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
