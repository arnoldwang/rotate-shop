package com.dianping.rotate.shop.listener;

import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.exception.POIMessageException;
import com.dianping.rotate.shop.factory.POIChange;
import com.dianping.rotate.shop.factory.POIFactory;
import com.dianping.swallow.common.message.Message;
import com.dianping.swallow.consumer.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/7.
 */
public class POIShopStatusListener implements MessageListener {
    @Autowired
    POIFactory poiFactory;

    @Override
    public void onMessage(Message msg) throws POIMessageException {
        POIChange poiChange = poiFactory.factory(POIMessageType.SHOP_STATUS);
        if(poiChange==null) return;
        poiChange.addToMsgQueue(msg);
    }
}
