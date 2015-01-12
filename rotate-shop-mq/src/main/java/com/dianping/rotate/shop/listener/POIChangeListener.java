package com.dianping.rotate.shop.listener;

import com.dianping.rotate.shop.exception.POIMessageException;
import com.dianping.rotate.shop.factory.POIChange;
import com.dianping.rotate.shop.factory.POIFactory;
import com.dianping.rotate.shop.service.POIChangeService;
import com.dianping.swallow.common.message.Message;
import com.dianping.swallow.consumer.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by zaza on 15/1/5.
 */
public class POIChangeListener implements MessageListener {
    @Autowired
    private POIFactory poiFactory;

    @Override
    public void onMessage(Message msg) throws POIMessageException{
        int msgType = POIChangeService.getPOIChangeMessageType(msg);
        POIChange poiChange = poiFactory.factory(msgType);
        if(poiChange==null)return;
        poiChange.addToMsgQueue(msg);
    }
}
