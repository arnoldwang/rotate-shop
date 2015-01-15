package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.constants.BizType;
import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by yangjie on 1/14/15.
 */
public class AddShopByUserMessageRunner extends AbstractMessageRunner {

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
	@SuppressWarnings("unchecked")
    public void doMessage(MessageEntity msg) {
        try{
			Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg.getMsg());
			int shopId = (Integer) ((Map<String, Object>)msgBody.get("pair")).get("shopId");
			shopService.addShop(shopId);
            publishMessageToMQ(shopId, BizType.TUAN_HUI_6, ActionType.INSERT);
            markMessageHasDone(msg);
        }catch(Exception ex){
            markMessageHasFailed(msg);
            logger.error(ex.getMessage(),ex);
        }
    }
}
