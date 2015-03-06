package com.dianping.rotate.shop.producer;

import com.dianping.swallow.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

/**
 * Created by zaza on 15/3/6.
 */
public abstract class AbstractMessageProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("jobProducerClient")
    private Producer jobProducerClient;

    abstract String getMessageJson() throws IOException;

    public void send(){
        try{
            String msg = getMessageJson();
            jobProducerClient.sendMessage(msg);
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }
}
