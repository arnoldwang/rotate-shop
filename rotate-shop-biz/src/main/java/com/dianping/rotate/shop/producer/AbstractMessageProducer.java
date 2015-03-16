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
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("rotateGroupProducerClient")
    protected Producer rotateGroupProducerClient;
    @Autowired
    @Qualifier("rotateGroupShopProducerClient")
    protected Producer rotateGroupShopProducerClient;
    @Autowired
    @Qualifier("apolloShopProducerClient")
    protected  Producer apolloShopProducerClient;

    abstract void send(String msg);
}
