package com.dianping.rotate.shop.listener;

import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.factory.POIChange;
import com.dianping.rotate.shop.factory.POIFactory;
import com.dianping.rotate.shop.utils.Switch;
import com.dianping.swallow.common.message.Message;
import com.dianping.swallow.consumer.BackoutMessageException;
import com.dianping.swallow.consumer.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/7.
 */
public class POIShopStatusListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private POIFactory poiFactory;

    @Override
    public void onMessage(Message msg) throws BackoutMessageException{
        try{
            if(Switch.off()) return;
            if (Switch.notAccept()) throw new BackoutMessageException("Not Accept Message");
            POIChange poiChange = poiFactory.factory(POIMessageType.SHOP_STATUS);
            if(poiChange==null) return;
            poiChange.addToMsgQueue(msg);
        }catch(Exception ex){
            logger.error("Accept POI Shop Status Message Error:"+ex.getMessage(),ex);
            throw new BackoutMessageException("Accept POI Shop Status Message Failed");
        }

    }
}
