package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.json.MessageEntity;

/**
 * User: zhenwei.wang
 * Date: 15-1-15
 */
public class RegionMessageRunner extends AbstractMessageRunner {
	@Override
	int getMessageSourceType() {
		return MessageSource.PERSON;
	}

	@Override
	int getPOIMessageType() {
		return POIMessageType.SHOP_REGION;
	}

	@Override
	public void doMessage(MessageEntity message) {
		//To change body of implemented methods use File | Settings | File Templates.

	}
}
