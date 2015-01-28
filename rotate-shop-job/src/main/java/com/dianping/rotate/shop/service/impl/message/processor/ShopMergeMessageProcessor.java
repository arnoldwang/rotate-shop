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
@Service
public class ShopMergeMessageProcessor extends AbstractMessageProcessor {
    public void process(MessageEntity message) throws Exception{
        Map<String, Object> msgMap = JsonUtil.fromStrToMap(message.getMsg());
        int shopId = (Integer)msgMap.get("ShopID");
        shopService.closeShop(shopId);
        sendMessage(new ShopMessage(shopId, ActionType.UPDATE));
    }
}
