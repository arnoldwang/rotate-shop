package com.dianping.rotate.shop.service.impl;

import com.beust.jcommander.internal.Lists;
import com.dianping.rotate.shop.dao.RotateGroupDAO;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.entity.ApolloShopEntity;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;
import com.dianping.rotate.shop.entity.RotateGroupEntity;
import com.dianping.rotate.shop.entity.RotateGroupShopEntity;
import com.dianping.rotate.shop.factory.impl.TPApolloShopExtend;
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
public class POIServiceImpl implements POIService {

	@Autowired
	ShopService shopService;

	@Autowired
	RotateGroupDAO rotateGroupDAO;

	@Autowired
	RotateGroupShopDAO rotateGroupShopDAO;

	@Override
	public void addPoiBySys(String msg) {
		try {
			Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg);
			int shopId = Integer.valueOf((String) msgBody.get("shopId"));
			ShopDTO shopDTO = shopService.loadShop(shopId);
			ApolloShopEntity apolloShopEntity = createApolloShop(shopDTO);
			List<ApolloShopExtendEntity> apolloShopExtendList = createApolloShopExtendList(shopId);
			for (ApolloShopExtendEntity apolloShopExtend : apolloShopExtendList) {
				List<RotateGroupShopEntity> rotateGroupShopList = rotateGroupShopDAO.queryRotateGroupShopByShopGroupID(shopDTO.getShopGroupId(), apolloShopExtend.getBizID());
				if (rotateGroupShopList.size() == 0) {
					List<Integer> rotateGroupIDList = insertRotateGroupList(apolloShopExtendList);
					List<RotateGroupShopEntity> newRotateGroupShopList = insertRotateGroupShopList(rotateGroupIDList, apolloShopEntity);
				} else if (rotateGroupShopList.size() == 1) {
					//todo create RotateGroupShop
					int rotateGroupID = rotateGroupShopList.get(0).getRotateGroupID();
					updateRotateGroup(rotateGroupID);
					insertRotateGroupShop(rotateGroupID, apolloShopEntity);
				} else {
					//todo find smaller RotateGroupID, create RotateGroupShop
				}
			}
		} catch (Exception e) {
			//todo
		}
	}

	private void updateRotateGroup(int rotateGroupID) {
		RotateGroupEntity rotateGroupEntity = rotateGroupDAO.queryRotateGroup(rotateGroupID).get(0);
		rotateGroupEntity.setType(1);
		rotateGroupDAO.updateRotateGroup(rotateGroupEntity);
	}

	private RotateGroupShopEntity insertRotateGroupShop(int rotateGroupID, ApolloShopEntity apolloShopEntity) {
		List<Integer> rotateGroupIDList = Lists.newArrayList(rotateGroupID);
		List<RotateGroupShopEntity> rotateGroupShopEntityList = insertRotateGroupShopList(rotateGroupIDList, apolloShopEntity);
		return rotateGroupShopEntityList.get(0);
	}

	private List<RotateGroupShopEntity> insertRotateGroupShopList(List<Integer> rotateGroupIDList, ApolloShopEntity apolloShopEntity) {
		List<RotateGroupShopEntity> rotateGroupShopEntityList = Lists.newArrayList();
		for (Integer rotateGroupID : rotateGroupIDList) {
			RotateGroupShopEntity rotateGroupShopEntity = new RotateGroupShopEntity();
			rotateGroupShopEntity.setRotateGroupID(rotateGroupID);
			rotateGroupShopEntity.setShopID(apolloShopEntity.getShopID());
			rotateGroupShopEntity.setShopGroupID(apolloShopEntity.getShopGroupID());
			rotateGroupShopEntity.setStatus(1);
			rotateGroupShopEntityList.add(rotateGroupShopEntity);
		}
		rotateGroupShopDAO.addToRotateGroupShopByList(rotateGroupShopEntityList);
		return rotateGroupShopEntityList;
	}

	private List<Integer> insertRotateGroupList(List<ApolloShopExtendEntity> apolloShopExtendList) {
		List<Integer> rotateGroupIDList = Lists.newArrayList();
		for (ApolloShopExtendEntity apolloShopExtend : apolloShopExtendList) {
			if (apolloShopExtend != null) {
				RotateGroupEntity rotateGroupEntity = new RotateGroupEntity();
				rotateGroupEntity.setBizID(apolloShopExtend.getBizID());
				rotateGroupEntity.setType(0);
				rotateGroupEntity.setStatus(1);
				int rotateGroupID = rotateGroupDAO.addToRotateGroup(rotateGroupEntity);
				rotateGroupIDList.add(rotateGroupID);
			}
		}
		return rotateGroupIDList;
	}

	private ApolloShopEntity createApolloShop(ShopDTO shopDTO) {
		ApolloShopEntity apolloShopEntity = new ApolloShopEntity();
		apolloShopEntity.setShopID(shopDTO.getShopId());
		apolloShopEntity.setShopGroupID(shopDTO.getShopGroupId());
		apolloShopEntity.setCityID(shopDTO.getCityId());
		apolloShopEntity.setDistrict(shopDTO.getDistrict());
		apolloShopEntity.setShopType(shopDTO.getShopType());
		apolloShopEntity.setStatus(1);

		return apolloShopEntity;
	}

	private List<ApolloShopExtendEntity> createApolloShopExtendList(int shopID) {
		List<ApolloShopExtendEntity> apolloShopExtendList = Lists.newArrayList();
		ApolloShopExtendEntity tp_ApolloShopExtendEntity = new TPApolloShopExtend().createApolloShopExtend(shopID);
		apolloShopExtendList.add(tp_ApolloShopExtendEntity);
		//add other Bu apolloShopExtendEntity

		return apolloShopExtendList;
	}
}
