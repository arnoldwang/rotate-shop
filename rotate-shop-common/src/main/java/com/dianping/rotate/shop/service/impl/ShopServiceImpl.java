package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.dao.*;
import com.dianping.rotate.shop.entity.*;
import com.dianping.rotate.shop.factory.impl.TPApolloShopExtend;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.utils.JsonUtil;
import com.dianping.shopremote.remote.dto.ShopCategoryDTO;
import com.dianping.shopremote.remote.dto.ShopDTO;
import com.dianping.shopremote.remote.dto.ShopRegionDTO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by yangjie on 1/13/15.
 */
@Service
public class ShopServiceImpl implements ShopService {

	Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	com.dianping.shopremote.remote.ShopService shopService;

	@Autowired
	ApolloShopDAO apolloShopDAO;

	@Autowired
	ApolloShopExtendDAO apolloShopExtendDAO;

	@Autowired
	RotateGroupDAO rotateGroupDAO;

	@Autowired
	RotateGroupShopDAO rotateGroupShopDAO;

	@Autowired
	ShopCategoryDAO shopCategoryDAO;

	@Autowired
	ShopRegionDAO shopRegionDAO;

    @Override
    public void closeShop(int shopId) {
		apolloShopDAO.deleteApolloShopByShopID(shopId);

		updateRotateGroupTypeAndStatusByShopID(shopId);
    }

	@Override
	public void openShop(int shopId) {
		apolloShopDAO.restoreApolloShopByShopID(shopId);

		updateRotateGroupTypeAndStatusByShopID(shopId);
	}

	@Override
	public void updateRotateGroupTypeAndStatusByShopID(int shopId) {
		for (RotateGroupShopEntity group: rotateGroupShopDAO.queryRotateGroupShopByShopID(shopId)) {
			updateRotateGroupTypeAndStatusByRotateGroupId(group.getRotateGroupID());
		}
	}

	@Override
	public void updateRotateGroupTypeAndStatusByRotateGroupId(int rotateGroupID) {

		// 这里需要取出所有的轮转组，包括已经删掉的，因为这里需要根据轮转组下的门店状态重置轮转组的status
		RotateGroupEntity rotateGroup = rotateGroupDAO.getRotateGroupIgnoreStatus(rotateGroupID);
		// 如果没有这个轮转组，就不操作
		if (rotateGroup == null) {
			return;
		}

		int shopCountInThisRotateGroup;

		List<RotateGroupShopEntity> r = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);

		if (r.size() == 0) {
			shopCountInThisRotateGroup = 0;
		} else {
			// 这里过滤，只取正常营业的门店
			List<ApolloShopEntity> shops = apolloShopDAO.queryApolloShopByShopIDList(Lists.transform(r, new Function<RotateGroupShopEntity, Integer>() {
				@Override
				public Integer apply(RotateGroupShopEntity rotateGroupShopEntity) {
					return rotateGroupShopEntity.getShopID();
				}
			}));

			shopCountInThisRotateGroup = shops.size();
		}


		if (shopCountInThisRotateGroup > 0) {
			// 有门店,设为有效
			rotateGroup.setStatus(1);

			if (shopCountInThisRotateGroup > 1) {
				// 大于1家门店,设为连锁店
				rotateGroup.setType(1);
			} else {
				// 单店
				rotateGroup.setType(0);
			}
		} else {
			// 没有门店，设为无效
			rotateGroup.setStatus(0);
		}

		rotateGroupDAO.updateRotateGroup(rotateGroup);
	}

	@Override
	public void updatePoi(String msg) {
		try {
			Map<String, Object> msgBody = JsonUtil.fromStrToMap(msg);
			int shopId = (Integer) msgBody.get("shopId");
			ShopDTO shopDTO = shopService.loadShop(shopId);
			if (shopDTO == null)
				return;
			ApolloShopEntity apolloShopEntity = apolloShopDAO.queryApolloShopByShopIDWithNoStatus(shopId);
			if (apolloShopEntity.getShopStatus() != shopDTO.getPower()) {
				updateRotateGroupTypeAndStatusByShopID(shopId);
			}
			if (apolloShopEntity.getShopGroupID() != shopDTO.getShopGroupId()) {
				//todo 跟新轮转组门店关系表
			}
			updateApolloShop(apolloShopEntity, shopDTO);
		} catch (IOException e) {
			logger.warn("update shop info failed", e);
		}
	}

	@Override
	public void addShop(int shopId) {
		ShopDTO shopDTO = shopService.loadShop(shopId);
		if (shopDTO == null) {
			logger.info("add shop info failed with wrong shopId!");
			return;
		}
		List<ShopCategoryDTO> shopCategoryDTOList = shopService.findShopCategories(shopId, shopDTO.getCityId());
		List<ShopRegionDTO> shopRegionDTOList = shopService.findShopRegions(shopId);
		if (shopCategoryDTOList == null || shopCategoryDTOList.size() == 0) {
			logger.info("add shop info failed with having no category!");
			return;
		}
		if (shopRegionDTOList == null || shopRegionDTOList.size() == 0) {
			logger.info("add shop info failed with having no region!");
			return;
		}
		if (apolloShopDAO.queryApolloShopByShopIDWithNoStatus(shopId) != null) {
			logger.info("add shop info failed with shop existed!");
			return;
		}
		ApolloShopEntity apolloShopEntity = insertApolloShop(shopDTO);
		insertShopCategoryList(shopCategoryDTOList, shopDTO);
		insertShopRegionList(shopRegionDTOList, shopDTO);
		List<ApolloShopExtendEntity> apolloShopExtendList = insertApolloShopExtendList(shopId);//按Bu创建ApolloShopExtend
		for (ApolloShopExtendEntity apolloShopExtend : apolloShopExtendList) {
			List<RotateGroupShopEntity> rotateGroupShopList = rotateGroupShopDAO.queryRotateGroupShopByShopGroupIDAndBizID(shopDTO.getShopGroupId(), apolloShopExtend.getBizID());
			if (rotateGroupShopList.size() == 0) {
				//此处有坑，当一个新店与一个已存在的老店shopGroupID相同，而老店被关闭时，轮转组size为0，会新增一个轮转组
				int rotateGroupID = insertRotateGroup(apolloShopExtend);
				insertRotateGroupShop(rotateGroupID, apolloShopEntity);
			} else {
				int rotateGroupID = rotateGroupShopList.get(0).getRotateGroupID();
				setRotateGroupToStores(rotateGroupID);//更新轮转组type
				insertRotateGroupShop(rotateGroupID, apolloShopEntity);
			}
		}
	}

	private void insertShopRegionList(List<ShopRegionDTO> shopRegionDTOList, ShopDTO shopDTO) {
		List<ShopRegionEntity> shopRegionEntityList = Lists.newArrayList();
		int mainRegionId = shopDTO.getMainRegionId();
		int shopId = shopDTO.getShopId();
		for (ShopRegionDTO shopRegionDTO : shopRegionDTOList) {
			ShopRegionEntity shopRegionEntity = new ShopRegionEntity();
			shopRegionEntity.setShopID(shopId);
			//todo check whether right ID
			shopRegionEntity.setRegionID(shopRegionDTO.getRegionId());
			shopRegionEntity.setIsMain(mainRegionId == shopRegionDTO.getRegionId() ? 1 : 0);
			shopRegionEntity.setStatus(1);
			shopRegionEntityList.add(shopRegionEntity);
		}
		shopRegionDAO.addShopRegionByList(shopRegionEntityList);
	}

	private void insertShopCategoryList(List<ShopCategoryDTO> shopCategoryDTOList, ShopDTO shopDTO) {
		List<ShopCategoryEntity> shopCategoryEntityList = Lists.newArrayList();
		int mainCategoryId = shopDTO.getMainCategoryId();
		int shopId = shopDTO.getShopId();
		for (ShopCategoryDTO shopCategoryDTO : shopCategoryDTOList) {
			ShopCategoryEntity shopCategoryEntity = new ShopCategoryEntity();
			shopCategoryEntity.setShopID(shopId);
			//todo check whether right ID
			shopCategoryEntity.setCategoryID(shopCategoryDTO.getId());
			shopCategoryEntity.setIsMain(mainCategoryId == shopCategoryDTO.getId() ? 1 : 0);
			shopCategoryEntity.setStatus(1);
			shopCategoryEntityList.add(shopCategoryEntity);
		}
		shopCategoryDAO.addShopCategoryByList(shopCategoryEntityList);
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
				rotateGroupEntity.setType(0);//0：单店；1：连锁店
				rotateGroupEntity.setStatus(1);
				int rotateGroupID = rotateGroupDAO.addToRotateGroup(rotateGroupEntity);
				rotateGroupIDList.add(rotateGroupID);
			}
		}
		return rotateGroupIDList;
	}

	private void setRotateGroupToStores(int rotateGroupID) {
		RotateGroupEntity rotateGroupEntity = rotateGroupDAO.getRotateGroup(rotateGroupID);
		rotateGroupEntity.setType(1);//0：单店；1：连锁店
		rotateGroupDAO.updateRotateGroup(rotateGroupEntity);
	}

	private ApolloShopEntity insertApolloShop(ShopDTO shopDTO) {
		ApolloShopEntity apolloShopEntity = new ApolloShopEntity();
		apolloShopEntity.setShopID(shopDTO.getShopId());
		apolloShopEntity.setShopGroupID(shopDTO.getShopGroupId());
		apolloShopEntity.setCityID(shopDTO.getCityId());
		apolloShopEntity.setDistrict(shopDTO.getDistrict());
		apolloShopEntity.setShopType(shopDTO.getShopType());
		apolloShopEntity.setShopStatus(shopDTO.getPower());

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


	private void updateApolloShop(ApolloShopEntity apolloShopEntity, ShopDTO shopDTO) {
		apolloShopEntity.setShopGroupID(shopDTO.getShopGroupId());
		apolloShopEntity.setShopType(shopDTO.getShopType());
		apolloShopEntity.setCityID(shopDTO.getCityId());
		apolloShopEntity.setDistrict(shopDTO.getDistrict());
		apolloShopEntity.setShopStatus(shopDTO.getPower());
		apolloShopDAO.updateApolloShop(apolloShopEntity);
	}
}
