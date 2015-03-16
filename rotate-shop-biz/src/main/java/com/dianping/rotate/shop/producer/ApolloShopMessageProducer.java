package com.dianping.rotate.shop.producer;

import org.springframework.stereotype.Service;

/**
 * Created by zaza on 15/3/16.
 */
@Service("apolloShopMessageProducer")
public class ApolloShopMessageProducer extends AbstractMessageProducer {
    public void send(String msg){
        try{
            apolloShopProducerClient.sendMessage(msg);
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }
}
