package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.utils.JsonUtil;

import java.util.Map;

/**
 * User: zhenwei.wang
 * Date: 15-1-15
 */
public class ShopUpdateMessageRunner extends AbstractMessageRunner {
	@Override
	int getMessageSourceType() {
		return MessageSource.PERSON;
	}

	@Override
	int getPOIMessageType() {
		return POIMessageType.SHOP_UPDATE;
	}

	@Override
	public void doMessage(MessageEntity message) throws Exception{
        Map<String, Object> msg = JsonUtil.fromStrToMap(message.getMsg());
        int shopId = (Integer)msg.get("shopId");
        shopService.updateShop(message.getMsg());
	}
}
