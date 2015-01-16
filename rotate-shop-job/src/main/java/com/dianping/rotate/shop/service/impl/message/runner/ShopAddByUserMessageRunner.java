package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.constants.BizType;
import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.json.ShopMessage;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

import java.util.Map;

/**
 * Created by yangjie on 1/14/15.
 */
// 这里不要加@Service 因为在被引用的时候是根据class新生成一个实例
public class ShopAddByUserMessageRunner extends AbstractMessageRunner {

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
    public void doMessage(MessageEntity msg) throws Exception {
        Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg.getMsg());
        int shopId = (Integer) ((Map<String, Object>)msgBody.get("pair")).get("shopId");
        shopService.addShop(shopId);
        publishMessageToMQ(new ShopMessage(shopId,ActionType.INSERT));
    }
}
