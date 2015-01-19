package com.dianping.rotate.shop.listener;

import com.dianping.rotate.shop.exception.POIMessageException;
import com.dianping.rotate.shop.factory.POIChange;
import com.dianping.rotate.shop.factory.POIFactory;
import com.dianping.rotate.shop.service.POIChangeService;
import com.dianping.swallow.common.message.Message;
import com.dianping.swallow.consumer.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by zaza on 15/1/5.
 */
public class POIChangeListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private POIFactory poiFactory;

    @Override
    public void onMessage(Message msg) throws POIMessageException{
        try{
            int msgType = POIChangeService.getPOIChangeMessageType(msg);
            POIChange poiChange = poiFactory.factory(msgType);
            if(poiChange==null)return;
            poiChange.addToMsgQueue(msg);
        }catch(Exception ex){
            logger.error("Accept POI Change Message Error:"+ex.getMessage(),ex);
        }

    }
}
