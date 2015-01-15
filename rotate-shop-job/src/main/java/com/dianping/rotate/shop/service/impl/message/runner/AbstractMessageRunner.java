package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.service.impl.ShopMessageProducer;
import com.dianping.rotate.shop.utils.Switch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by yangjie on 1/14/15.
 */
public abstract class AbstractMessageRunner implements Runnable {
    private static final int PROCESS_MESSAGE_LIMIT = 10;
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static final int MAX_RETRY = 10;

    abstract int getMessageSourceType();
    abstract int getPOIMessageType();
    abstract void doMessage(MessageEntity message);

    protected void markMessageHasDone(MessageEntity msg) {
        messageDAO.deleteMessage(msg.getId());
    }

    protected void markMessageHasFailed(MessageEntity msg) {
        messageDAO.updateMessageAttemptIndex(msg.getId(),msg.getAttemptIndex()+1);
    }

    protected void publishMessageToMQ(int shopId,String action) {
        shopMessageProducer.send(shopId,action);
    }

    @Autowired
    private ShopMessageProducer shopMessageProducer;

    @Autowired
    private MessageQueueDAO messageQueueDAO;

    @Autowired
    private MessageQueueDAO messageDAO;

    @Override
    public void run() {
        while(true){
            try {
                if(Switch.on()){
                    List<MessageEntity> messages = new ArrayList<MessageEntity>();
                    int attemptIndex = 0;
                    while(messages.size()<1){
                        messages = messageQueueDAO.getMessage(getMessageSourceType(), getPOIMessageType(),
                                attemptIndex,PROCESS_MESSAGE_LIMIT);
                        attemptIndex = attemptIndex > MAX_RETRY ? 0 : attemptIndex+1;
                    }
                    Collection<Callable<Void>> tasks=new ArrayList<Callable<Void>>();
                    for(final MessageEntity msg:messages){
                        tasks.add(new Callable<Void>() {
                            @Override
                            public Void call() throws Exception {
                                doMessage(msg);
                                return null;
                            }
                        });
                    }
                    List<Future<Void>> futures = threadPool.invokeAll(tasks);
                    for(Future<Void> future:futures){
                        future.get();
                    }
                }
            }catch(Exception ex){
                logger.error(ex.getMessage(), ex);
            }
        }
    }
}
