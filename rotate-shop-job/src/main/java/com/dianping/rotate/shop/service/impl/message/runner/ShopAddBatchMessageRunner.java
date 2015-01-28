package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;

/**
 * Created by zaza on 15/1/28.
 */
public class ShopAddBatchMessageRunner extends BatchMessageRunner {
    @Override
    int getMessageSourceType() {
        return MessageSource.SYSTEM;
    }

    @Override
    int getPOIMessageType() {
        return POIMessageType.SHOP_ADD;
    }
}
