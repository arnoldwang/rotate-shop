package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.json.ShopMessage;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * User: zhenwei.wang
 * Date: 15-1-15
 */
public class UpdateShopMessageRunner extends AbstractMessageRunner {

	@Autowired
	ShopService shopService;

	@Override
	int getMessageSourceType() {
		return MessageSource.PERSON;
	}

	@Override
	int getPOIMessageType() {
		return POIMessageType.SHOP_UPDATE;
	}

	@Override
	public void doMessage(MessageEntity message) throws Exception {
		Map<String, Object> msgBody = JsonUtil.fromStrToMap(message.getMsg());
		int shopId = (Integer) msgBody.get("shopId");
		shopService.updateShop(shopId);
		publishMessageToMQ(new ShopMessage(shopId, ActionType.UPDATE));
	}
}
