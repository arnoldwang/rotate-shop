package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.service.MessageProducer;
import com.dianping.swallow.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zaza on 15/1/14.
 */
@Service
public class ShopMessageProducer implements MessageProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    Producer producerClient;
    @Autowired
    MessageProducerService messageProducerService;

    @Override
    public void send(int shopId,String action){
        try{
            String msg = messageProducerService.getShopMessageJson(shopId,action);
            producerClient.sendMessage(msg);
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }

    }
}
