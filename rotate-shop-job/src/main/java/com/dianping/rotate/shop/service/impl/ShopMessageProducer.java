package com.dianping.rotate.shop.service.impl;

import com.dianping.cat.message.Message;
import com.dianping.rotate.shop.service.MessageProducer;
import com.dianping.swallow.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/14.
 */
public class ShopMessageProducer implements MessageProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    Producer producerClient;
    @Autowired
    MessageProducerService messageProducerService;

    @Override
    public void send(int shopId,int bizType,String action){
        try{
            String msg = messageProducerService.getShopMessageJson(shopId,bizType,action);
            producerClient.sendMessage(msg);
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }

    }
}
