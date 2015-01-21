package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.json.ShopMessage;
import com.dianping.rotate.shop.utils.JsonUtil;

import java.util.Map;

/**
 * User: zaza
 * Date: 15-1-15
 */
public class ShopStatusBySystemMessageRunner extends AbstractMessageRunner {
	@Override
	int getMessageSourceType() {
		return MessageSource.SYSTEM;
	}

	@Override
	int getPOIMessageType() {
		return POIMessageType.SHOP_STATUS;
	}

	@Override
	public void doMessage(MessageEntity message) throws Exception {
		Map<String, Object> msgBody = JsonUtil.fromStrToMap(message.getMsg());
		int shopId = (Integer) msgBody.get("shopId");
		shopService.updateShop(shopId);
		publishMessageToMQ(new ShopMessage(shopId, ActionType.UPDATE));
	}
}
