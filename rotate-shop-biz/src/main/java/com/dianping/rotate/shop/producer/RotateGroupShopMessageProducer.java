package com.dianping.rotate.shop.producer;


import org.springframework.stereotype.Service;

/**
 * Created by zaza on 15/3/11.
 */
@Service("rotateGroupShopMessageProducer")

public class RotateGroupShopMessageProducer extends AbstractMessageProducer{
    public void send(String msg){
        try{
            rotateGroupShopProducerClient.sendMessage(msg);
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }
}
