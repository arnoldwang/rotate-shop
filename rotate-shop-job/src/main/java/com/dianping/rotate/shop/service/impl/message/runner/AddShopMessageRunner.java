package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.constants.BizType;
import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.rotate.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangjie on 1/14/15.
 */
public class AddShopMessageRunner extends AbstractMessageRunner {

    @Autowired
    protected ShopService shopService;

    @Override
    int getMessageSourceType() {
        return MessageSource.PERSON;
    }

    @Override
    int getPOIMessageType() {
        return POIMessageType.SHOP_ADD;
    }

    @Override
    public void doMessage(MessageEntity msg) {
        try{
            shopService.addPoiByUser(msg.getMsg());
            publishMessageToMQ(123, BizType.TUAN_HUI_6, ActionType.INSERT);
            markMessageHasDone(msg);
        }catch(Exception ex){
            markMessageHasFailed(msg);
            logger.error(ex.getMessage(),ex);
        }
    }
}
