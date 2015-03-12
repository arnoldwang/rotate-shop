package com.dianping.rotate.shop.producer;

import org.springframework.stereotype.Service;

/**
 * Created by zaza on 15/3/11.
 */
@Service("rotateGroupMessageProducer")
public class RotateGroupMessageProducer extends AbstractMessageProducer {
    public void send(String msg){
        try{
            rotateGroupProducerClient.sendMessage(msg);
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }
}
