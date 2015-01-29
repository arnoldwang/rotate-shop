package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.service.MessageProcessor;
import javax.annotation.Resource;

/**
 * Created by zaza on 15/1/28.
 */
public class ShopUpdateBatchMessageRunner extends SingleMessageRunner {
    @Resource(name="shopUpdateMessageProcessor")
    private MessageProcessor messageProcessor;

    @Override
    int getMessageSourceType() {
        return MessageSource.SYSTEM;
    }

    @Override
    int getPOIMessageType() {
        return POIMessageType.SHOP_UPDATE;
    }

    @Override
    MessageProcessor getMessageProcessor(){
        return this.messageProcessor;
    }
}
