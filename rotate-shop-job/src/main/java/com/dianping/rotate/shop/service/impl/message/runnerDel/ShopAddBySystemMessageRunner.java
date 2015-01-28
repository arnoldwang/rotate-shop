package com.dianping.rotate.shop.service.impl.message.runnerDel;

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
//系统行为暂时未开放
public class ShopAddBySystemMessageRunner extends AbstractMessageRunner {
	@Autowired
	ShopService shopService;

	@Override
	int getMessageSourceType() {
		return MessageSource.SYSTEM;
	}

	@Override
	int getPOIMessageType() {
		return POIMessageType.SHOP_ADD_BATCH;
	}

	@Override
	public void doMessage(MessageEntity msg) throws Exception {
		Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg.getMsg());
		int shopId = (Integer) msgBody.get("shopId");
		shopService.addShop(shopId);
		publishMessageToMQ(new ShopMessage(shopId,ActionType.INSERT));
	}
}
