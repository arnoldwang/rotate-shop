package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.constants.BizType;
import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangjie on 1/14/15.
 */
@Service
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
            Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg.getMsg());
            int shopId = (Integer)((Map<String, Object>)msgBody.get("pair")).get("shopId");
            publishMessageToMQ(shopId,ActionType.INSERT);
            markMessageHasDone(msg);
        }catch(Exception ex){
            markMessageHasFailed(msg);
            logger.error(ex.getMessage(),ex);
        }
    }
}
