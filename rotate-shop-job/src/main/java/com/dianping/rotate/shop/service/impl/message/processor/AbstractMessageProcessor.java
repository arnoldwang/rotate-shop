package com.dianping.rotate.shop.service.impl.message.processor;

import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.service.MessageProcessor;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.service.impl.message.producer.ShopMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/20.
 */
public abstract class AbstractMessageProcessor implements MessageProcessor{
    @Autowired
    protected ShopService shopService;
    @Autowired
    private ShopMessageProducer shopMessageProducer;

    abstract public void process(MessageEntity message) throws Exception;

    protected void sendMessage(Object msg) {
        shopMessageProducer.send(msg);
    }
}
