package com.dianping.roate.shop.listener;

import com.dianping.roate.shop.exception.POIMessageException;
import com.dianping.roate.shop.factory.POIChange;
import com.dianping.roate.shop.factory.POIFactory;
import com.dianping.roate.shop.service.POIChangeService;
import com.dianping.swallow.common.message.Message;
import com.dianping.swallow.consumer.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by zaza on 15/1/5.
 */
public class POIChangeListener implements MessageListener {

    @Override
    public void onMessage(Message msg) throws POIMessageException{
        int msgType = POIChangeService.getMessageType(msg);
        POIChange poiChange = POIFactory.factory(msgType);
        poiChange.addToMsgQueue(msg);
    }
}
