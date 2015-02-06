package com.dianping.rotate.shop.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: zhenwei.wang
 * Date: 15-2-3
 */
@Service
public class SwallowService {


	public Map<String, Object> createMsg(String msgType, String[] shopIDs) {
		Map<String, Object> msgMap = Maps.newHashMap();
		List<String> swallowMsgs = Lists.newArrayList();
		List<String> correctShopIDs = new ArrayList<String>(shopIDs.length);
		for(String shopID: shopIDs){
			if(isNumber(shopID))
				correctShopIDs.add(shopID);
		}
		if(msgType.equals("用户新增")){
			for(String shopID: correctShopIDs){
				StringBuilder swallowMsg = new StringBuilder("{\"type\":201,\"pair\":{\"shopId\":");
				swallowMsg.append(shopID);
				swallowMsg.append("}}");
				swallowMsgs.add(swallowMsg.toString());
			}
			msgMap.put("topic", "dp_action");
		}
		if(msgType.equals("系统新增")){
			for(String shopID: correctShopIDs){
				StringBuilder swallowMsg = new StringBuilder("{\"shopId\":");
				swallowMsg.append(shopID);
				swallowMsg.append(",\"messageType\":4}");
				swallowMsgs.add(swallowMsg.toString());
			}
			msgMap.put("topic", "dp_poi_change");
		}
		if(msgType.equals("POI变更")){
			for(String shopID: correctShopIDs){
				StringBuilder swallowMsg = new StringBuilder("{\"shopID\":");
				swallowMsg.append(shopID);
				swallowMsg.append(",\"lasgModifyUser\":-1234,\"messageType\":5}");
				swallowMsgs.add(swallowMsg.toString());
			}
			msgMap.put("topic", "dp_poi_change");
		}
		if(msgType.equals("合并商户")){
			for(String shopID: correctShopIDs){
				StringBuilder swallowMsg = new StringBuilder("{\"ShopID\":");
				swallowMsg.append(shopID);
				swallowMsg.append(",\"messageType\":2}");
				swallowMsgs.add(swallowMsg.toString());
			}
			msgMap.put("topic", "dp_poi_change");
		}
		if(msgType.equals("恢复商户")){
			for(String shopID: correctShopIDs){
				StringBuilder swallowMsg = new StringBuilder("{\"RestoreShopID\":");
				swallowMsg.append(shopID);
				swallowMsg.append(",\"messageType\":3}");
				swallowMsgs.add(swallowMsg.toString());
			}
			msgMap.put("topic", "dp_poi_change");
		}
		if(msgType.equals("商户状态改变")){
			for(String shopID: correctShopIDs){
				StringBuilder swallowMsg = new StringBuilder("{\"shopId\":");
				swallowMsg.append(shopID);
				swallowMsg.append(",\"lasgModifyUser\":-1234}");
				swallowMsgs.add(swallowMsg.toString());
			}
			msgMap.put("topic", "dp_shop_status_change");
		}
		msgMap.put("swallowMsgs", swallowMsgs);
		return msgMap;
	}

	private boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("-?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
