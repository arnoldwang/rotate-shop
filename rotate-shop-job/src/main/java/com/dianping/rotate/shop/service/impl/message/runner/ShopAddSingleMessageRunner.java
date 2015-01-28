package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;

/**
 * Created by zaza on 15/1/28.
 */
public class ShopAddSingleMessageRunner extends SingleMessageRunner {
    @Override
    int getMessageSourceType() {
        return MessageSource.PERSON;
    }

    @Override
    int getPOIMessageType() {
        return POIMessageType.SHOP_ADD;
    }
}
