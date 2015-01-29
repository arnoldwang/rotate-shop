package com.dianping.rotate.shop.service.impl.message.processor;

import com.dianping.pigeon.remoting.provider.config.annotation.Service;
import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.json.ShopMessage;
import com.dianping.rotate.shop.utils.JsonUtil;

import java.util.Map;

/**
 * Created by zaza on 15/1/28.
 */
public class ShopMergeRestoreMessageProcessor extends AbstractMessageProcessor {
    public void process(MessageEntity message) throws Exception{
        Map<String, Object> msg = JsonUtil.fromStrToMap(message.getMsg());
        int shopId = (Integer)msg.get("RestoreShopID");
        shopService.openShop(shopId);
        sendMessage(new ShopMessage(shopId, ActionType.UPDATE));
    }
}
