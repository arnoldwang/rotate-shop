package com.dianping.rotate.shop.listener;

import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.exception.POIMessageException;
import com.dianping.rotate.shop.factory.POIChange;
import com.dianping.rotate.shop.factory.POIFactory;
import com.dianping.rotate.shop.service.POIChangeService;
import com.dianping.swallow.common.message.Message;
import com.dianping.swallow.consumer.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/7.
 */
public class POIAddListener implements MessageListener {

    public static final int DP_ACTION_ADD_TYPE = 201;

    @Autowired
    private POIFactory poiFactory;

    @Override
    public void onMessage(Message msg) throws POIMessageException {
        if(POIChangeService.getDPActionMessageType(msg)==DP_ACTION_ADD_TYPE){
            POIChange poiChange = poiFactory.factory(POIMessageType.SHOP_ADD);
            if(poiChange==null) return;
            poiChange.addToMsgQueue(msg);
        }
    }
}
