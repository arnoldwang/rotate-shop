package com.dianping.rotate.shop.controller;

import com.dianping.rotate.shop.model.ServiceResult;
import com.dianping.rotate.shop.service.SwallowService;
import com.dianping.swallow.common.message.Destination;
import com.dianping.swallow.producer.Producer;
import com.dianping.swallow.producer.ProducerConfig;
import com.dianping.swallow.producer.ProducerMode;
import com.dianping.swallow.producer.impl.ProducerFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: zhenwei.wang
 * Date: 15-2-3
 */
@Controller
@RequestMapping("/swallow")
public class SwallowController {

	Logger logger = LoggerFactory.getLogger(SwallowController.class);

	@Autowired
	SwallowService swallowService;

	@RequestMapping(value = "/simulate", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ServiceResult simulateMsg(@RequestBody Msg msg) throws Exception {
		String[] shopIDs = msg.getShopID().replace(" ", "").split(",");
		Map<String, Object> msgMap = swallowService.createMsg(msg.getMsgType(), shopIDs);
		ProducerConfig config = new ProducerConfig();
		config.setMode(ProducerMode.SYNC_MODE);
		Producer p = ProducerFactoryImpl.getInstance().createProducer(Destination.topic((String) msgMap.get("topic")), config);
		List<String> swallowMsgs = (ArrayList<String>) msgMap.get("swallowMsgs");
		for (String swallowMsg : swallowMsgs) {
			p.sendMessage(swallowMsg);
		}
		return ServiceResult.success(swallowMsgs.size());
	}

	private static class Msg {
		String shopID;
		String msgType;

		private String getShopID() {
			return shopID;
		}

		private void setShopID(String shopID) {
			this.shopID = shopID;
		}

		private String getMsgType() {
			return msgType;
		}

		private void setMsgType(String msgType) {
			this.msgType = msgType;
		}
	}
}
