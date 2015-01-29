package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.service.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

/**
 * Created by zaza on 15/1/28.
 */
public class ShopMergeRestoreSingleMessageRunner extends SingleMessageRunner {
    @Resource(name="shopMergeRestoreMessageProcessor")
    private MessageProcessor messageProcessor;

    @Override
    int getMessageSourceType() {
        return MessageSource.PERSON;
    }

    @Override
    int getPOIMessageType() {
        return POIMessageType.SHOP_MERGE_RESTORE;
    }

    @Override
    MessageProcessor getMessageProcessor(){
        return this.messageProcessor;
    }
}
