package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.rotate.shop.service.MessageProcessService;
import com.dianping.rotate.shop.utils.Switch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
public class TaskRunner implements Runnable {
    private static final int PROCESS_MESSAGE_LIMIT = 10;
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final int MAX_RETRY = 10;

    @Autowired
    private MessageQueueDAO messageQueueDAO;

    @Autowired
    @Qualifier("messageProcessService")
    private MessageProcessService messageProcessService;


    private int messageSourceType = 0;
    private int POIMessageType = 0;

    public int getMessageSourceType() {
        return messageSourceType;
    }

    public void setMessageSourceType(int messageSourceType) {
        this.messageSourceType = messageSourceType;
    }

    public int getPOIMessageType() {
        return POIMessageType;
    }

    public void setPOIMessageType(int POIMessageType) {
        this.POIMessageType = POIMessageType;
    }

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
                                messageProcessService.process(getPOIMessageType(), msg);
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
