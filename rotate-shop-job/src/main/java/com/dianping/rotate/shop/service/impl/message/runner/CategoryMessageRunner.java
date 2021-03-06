package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.json.MessageEntity;

/**
 * User: zaza
 * Date: 15-1-15
 */
public class CategoryMessageRunner extends AbstractMessageRunner {
	@Override
	int getMessageSourceType() {
		return MessageSource.PERSON;
	}

	@Override
	int getPOIMessageType() {
		return POIMessageType.SHOP_CATEGORY;
	}

	@Override
	public void doMessage(MessageEntity message) {
		//To change body of implemented methods use File | Settings | File Templates.
        //对于分类树的变化，目前不不通知下游，只在后台记下，线下处理
	}
}
