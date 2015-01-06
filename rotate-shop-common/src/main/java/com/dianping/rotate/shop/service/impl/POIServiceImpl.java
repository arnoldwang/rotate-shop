package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.entity.ApolloShopEntity;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;
import com.dianping.rotate.shop.service.POIService;
import com.dianping.rotate.shop.utils.JsonUtil;
import com.dianping.shopremote.remote.ShopService;
import com.dianping.shopremote.remote.dto.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

/**
 * User: zhenwei.wang
 * Date: 15-1-6
 */
public class POIServiceImpl implements POIService{

	@Autowired
	ShopService shopService;

	@Override
	public void addPoiBySys(String msg) {
		try {
			Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg);
			int shopId = Integer.valueOf((String)msgBody.get("shopId"));
			ShopDTO shopDTO = shopService.loadShop(shopId);
			ApolloShopEntity apolloShopEntity = createApolloShop(shopDTO);
			

		} catch (Exception e) {
			//todo
		}
	}

	private ApolloShopEntity createApolloShop(ShopDTO shopDTO){
		ApolloShopEntity apolloShopEntity = new ApolloShopEntity();
		apolloShopEntity.setShopID(shopDTO.getShopId());
		apolloShopEntity.setShopGroupID(shopDTO.getShopGroupId());
		//todo

		return apolloShopEntity;
	}
}
