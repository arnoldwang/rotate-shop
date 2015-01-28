package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.service.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by zaza on 15/1/28.
 */
public class RegionSingleMessageRunner extends SingleMessageRunner {
    @Autowired
    @Qualifier("regionMessageProcessor")
    private MessageProcessor messageProcessor;

    @Override
    int getMessageSourceType() {
        return MessageSource.PERSON;
    }

    @Override
    int getPOIMessageType() {
        return POIMessageType.SHOP_REGION;
    }

    @Override
    MessageProcessor getMessageProcessor(){
        return this.messageProcessor;
    }
}
