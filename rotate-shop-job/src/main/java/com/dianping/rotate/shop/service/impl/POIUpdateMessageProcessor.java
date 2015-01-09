package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.rotate.shop.service.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zaza on 15/1/8.
 */
public class POIUpdateMessageProcessor implements MessageProcessor {
    static final int PROCESS_MESSAGE_LIMIT = 10;
    ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Autowired
    MessageQueueDAO messageQueueDAO;
    @Autowired
    MessageProcessService messageProcessService;

    @Override
    public void process(){
        while(true){
            List<MessageEntity> messages = messageQueueDAO.getMessage(MessageSource.PERSON, POIMessageType.SHOP_UPDATE,PROCESS_MESSAGE_LIMIT);
            Collection<Callable<Integer>> tasks=new ArrayList<Callable<Integer>>();
            for(final MessageEntity msg:messages){
                tasks.add(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return messageProcessService.messageProcess(msg,POIMessageType.SHOP_UPDATE);
                    }
                });
            }
            try{
                List<Future<Integer>> futures = threadPool.invokeAll(tasks);
                for(Future<Integer> future:futures){
                    future.get();
                }
            }catch(Exception ex){

            }
        }

    }
}
