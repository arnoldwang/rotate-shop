package com.dianping.rotate.shop.service.impl.message.producer;

import com.dianping.rotate.shop.service.MessageProducer;
import com.dianping.swallow.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by zaza on 15/1/15.
 */
public abstract class AbstractMessageProducer implements MessageProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Producer producerClient;

    abstract String getMessageJson(Object msg) throws IOException;

    @Override
    public void send(Object msgJson){
        try{
            String msg = getMessageJson(msgJson);
            producerClient.sendMessage(msg);
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }
}
