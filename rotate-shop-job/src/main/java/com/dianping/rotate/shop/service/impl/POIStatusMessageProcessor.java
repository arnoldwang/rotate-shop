package com.dianping.rotate.shop.service.impl;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.rotate.shop.constants.LionKey;
import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.rotate.shop.service.MessageProcessor;
import com.dianping.rotate.shop.utils.Switch;
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
public class POIStatusMessageProcessor implements MessageProcessor {
    private static final int PROCESS_MESSAGE_LIMIT = 10;
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Autowired
    private MessageQueueDAO messageQueueDAO;
    @Autowired
    private MessageProcessService messageProcessService;

    @Override
    public void process(){
        while(Switch.on()){
            List<MessageEntity> messages = messageQueueDAO.getMessage(MessageSource.PERSON, POIMessageType.SHOP_STATUS,PROCESS_MESSAGE_LIMIT);
            Collection<Callable<Integer>> tasks=new ArrayList<Callable<Integer>>();
            for(final MessageEntity msg:messages){
                tasks.add(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return messageProcessService.messageProcess(msg,POIMessageType.SHOP_STATUS);
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
