package com.dianping.rotate.shop.service.impl;


import com.dianping.rotate.shop.dao.ApolloShopDAO;
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO;
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
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User: zhenwei.wang
 * Date: 15-1-6
 */
@Service("POIService")
public class POIServiceImpl implements POIService {

	@Autowired
	ShopService shopService;

	@Autowired
	ApolloShopDAO apolloShopDAO;

	@Autowired
	ApolloShopExtendDAO apolloShopExtendDAO;

	@Autowired
	RotateGroupDAO rotateGroupDAO;

	@Autowired
	RotateGroupShopDAO rotateGroupShopDAO;

	@Override
	@SuppressWarnings("unchecked")
	public void addPoiByUser(String msg) {
		try {
			Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg);
			int shopId = (Integer) ((List<Map<String, Object>>) msgBody.get("pair")).get(0).get("shopId");
			addPoi(shopId);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void addPoiBySys(String msg) {
		try {
			Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg);
			int shopId = (Integer)msgBody.get("shopId");
			addPoi(shopId);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private void addPoi(int shopId) {
		ShopDTO shopDTO = shopService.loadShop(shopId);
		ApolloShopEntity apolloShopEntity = insertApolloShop(shopDTO);
		List<ApolloShopExtendEntity> apolloShopExtendList = insertApolloShopExtendList(shopId);//按Bu创建ApolloShopExtend
		for (ApolloShopExtendEntity apolloShopExtend : apolloShopExtendList) {
			List<RotateGroupShopEntity> rotateGroupShopList = rotateGroupShopDAO.queryRotateGroupShopByShopGroupIDAndBizID(shopDTO.getShopGroupId(), apolloShopExtend.getBizID());
			if (rotateGroupShopList.size() == 0) {
				int rotateGroupID = insertRotateGroup(apolloShopExtend);//创建轮转组
				insertRotateGroupShop(rotateGroupID, apolloShopEntity);//创建轮转组和门店关系
			} else {
				int rotateGroupID = rotateGroupShopList.get(0).getRotateGroupID();
				updateRotateGroup(rotateGroupID);//更新轮转组type
				insertRotateGroupShop(rotateGroupID, apolloShopEntity);
			}
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

	private int insertRotateGroup(ApolloShopExtendEntity apolloShopExtend) {
		List<ApolloShopExtendEntity> apolloShopExtendList = Lists.newArrayList(apolloShopExtend);
		List<Integer> rotateGroupIDList = insertRotateGroupList(apolloShopExtendList);
		return rotateGroupIDList.get(0);
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

	private ApolloShopEntity insertApolloShop(ShopDTO shopDTO) {
		ApolloShopEntity apolloShopEntity = new ApolloShopEntity();
		apolloShopEntity.setShopID(shopDTO.getShopId());
		apolloShopEntity.setShopGroupID(shopDTO.getShopGroupId());
		apolloShopEntity.setCityID(shopDTO.getCityId());
		apolloShopEntity.setDistrict(shopDTO.getDistrict());
		apolloShopEntity.setShopType(shopDTO.getShopType());
		apolloShopEntity.setStatus(1);

		int id = apolloShopDAO.addApolloShop(apolloShopEntity);
		apolloShopEntity.setId(id);
		return apolloShopEntity;
	}

	private List<ApolloShopExtendEntity> insertApolloShopExtendList(int shopID) {
		List<ApolloShopExtendEntity> apolloShopExtendList = Lists.newArrayList();
		ApolloShopExtendEntity tp_ApolloShopExtendEntity = new TPApolloShopExtend().createApolloShopExtend(shopID);
		int tp_id = apolloShopExtendDAO.addApolloShopExtend(tp_ApolloShopExtendEntity);
		tp_ApolloShopExtendEntity.setId(tp_id);
		apolloShopExtendList.add(tp_ApolloShopExtendEntity);
		//add other Bu apolloShopExtendEntity

		return apolloShopExtendList;
	}
}
