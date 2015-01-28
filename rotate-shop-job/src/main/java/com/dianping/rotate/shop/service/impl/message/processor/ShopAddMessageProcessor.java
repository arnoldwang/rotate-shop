package com.dianping.rotate.shop.service.impl.message.processor;

import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.json.ShopMessage;
import com.dianping.rotate.shop.utils.JsonUtil;

import java.util.Map;

/**
 * Created by zaza on 15/1/28.
 */
public class ShopAddMessageProcessor extends AbstractMessageProcessor {
    public void process(MessageEntity message) throws Exception{
        Map<String, Object> msgBody = JsonUtil.fromStrToMap(message.getMsg());
        int shopId = (Integer) ((Map<String, Object>)msgBody.get("pair")).get("shopId");
        shopService.addShop(shopId);
        sendMessage(new ShopMessage(shopId, ActionType.INSERT));
    }
}
