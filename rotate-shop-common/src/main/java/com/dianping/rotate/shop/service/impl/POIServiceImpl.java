package com.dianping.rotate.shop.service.impl;

import com.beust.jcommander.internal.Lists;
import com.dianping.rotate.shop.entity.ApolloShopEntity;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;
import com.dianping.rotate.shop.service.POIService;
import com.dianping.rotate.shop.utils.JsonUtil;
import com.dianping.shopremote.remote.ShopService;
import com.dianping.shopremote.remote.dto.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * User: zhenwei.wang
 * Date: 15-1-6
 */
public class POIServiceImpl implements POIService{

	public enum biz{
		TUANTUI(1);

		private int bizID;

		biz(int bizID){
			this.bizID = bizID;
		}

		public int getBizID(){
			return bizID;
		}

	}

	@Autowired
	ShopService shopService;

	@Override
	public void addPoiBySys(String msg) {
		try {
			Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg);
			int shopId = Integer.valueOf((String)msgBody.get("shopId"));
			ShopDTO shopDTO = shopService.loadShop(shopId);
			ApolloShopEntity apolloShopEntity = createApolloShop(shopDTO);
			List<ApolloShopExtendEntity> apolloShopExtendList = Lists.newArrayList();
			ApolloShopExtendEntity r = new ApolloShopExtendEntity();
			r.setBizID(biz.TUANTUI.getBizID());

		} catch (Exception e) {
			//todo
		}
	}

	private ApolloShopEntity createApolloShop(ShopDTO shopDTO){
		ApolloShopEntity apolloShopEntity = new ApolloShopEntity();
		apolloShopEntity.setShopID(shopDTO.getShopId());
		apolloShopEntity.setShopGroupID(shopDTO.getShopGroupId());
		apolloShopEntity.setCityID(shopDTO.getCityId());
		apolloShopEntity.setDistrict(shopDTO.getDistrict());
		apolloShopEntity.setShopType(shopDTO.getShopType());
		apolloShopEntity.setStatus(1);

		return apolloShopEntity;
	}
}
