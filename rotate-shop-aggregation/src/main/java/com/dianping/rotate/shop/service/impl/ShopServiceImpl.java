package com.dianping.rotate.shop.service.impl;

import com.dianping.combiz.service.CityService;
import com.dianping.rotate.core.api.service.RotateGroupUserService;
import com.dianping.rotate.shop.constants.BizTypeEnum;
import com.dianping.rotate.shop.constants.RotateGroupTypeEnum;
import com.dianping.rotate.shop.dao.*;
import com.dianping.rotate.shop.exceptions.WrongShopInfoException;
import com.dianping.rotate.shop.factory.ApolloShopExtendFactory;
import com.dianping.rotate.shop.json.*;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.shopremote.remote.dto.ShopCategoryDTO;
import com.dianping.shopremote.remote.dto.ShopDTO;
import com.dianping.shopremote.remote.dto.ShopRegionDTO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yangjie on 1/13/15.
 */
public class ShopServiceImpl implements ShopService {

	Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	com.dianping.shopremote.remote.ShopService shopService;

	@Autowired
	RotateGroupUserService rotateGroupUserService;

	@Autowired
	CityService cityService;

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

	List<ApolloShopExtendFactory> extendFactories;

	public void setExtendFactories(List<ApolloShopExtendFactory> extendFactories) {
		this.extendFactories = extendFactories;
	}

	@Override
	public void addShop(int shopId) {
		ShopDTO shopDTO = shopService.loadShop(shopId);
		if (shopDTO == null) {
			throw new WrongShopInfoException("add shop info failed with wrong shopId!");
		}

		if (apolloShopDAO.queryApolloShopByShopIDWithNoStatus(shopId) != null) {
			throw new WrongShopInfoException("add shop info failed with shop existed!");
		}

		List<ShopCategoryDTO> shopCategoryDTOList = shopService.findShopCategories(shopId, shopDTO.getCityId());
		List<ShopRegionDTO> shopRegionDTOList = shopService.findShopRegions(shopId);

		ApolloShopEntity apolloShopEntity = insertApolloShop(shopDTO);
		if (CollectionUtils.isNotEmpty(shopCategoryDTOList))
			insertShopCategoryList(shopCategoryDTOList, shopDTO);
		if (CollectionUtils.isNotEmpty(shopRegionDTOList))
			insertShopRegionList(shopRegionDTOList, shopDTO);
		List<ApolloShopExtendEntity> apolloShopExtendList = insertApolloShopExtendList(shopId);
		for (ApolloShopExtendEntity apolloShopExtend : apolloShopExtendList) {
			List<RotateGroupShopEntity> rotateGroupShopList = rotateGroupShopDAO.queryRotateGroupShopByShopGroupIDAndBizID(shopDTO.getShopGroupId(), apolloShopExtend.getBizID());
			if (rotateGroupShopList.size() == 0) {
				//此处有坑，当一个新店与一个已存在的老店shopGroupID相同，而老店被关闭时，轮转组size为0，会新增一个轮转组
				int rotateGroupID = insertRotateGroup(apolloShopExtend);
				insertRotateGroupShop(rotateGroupID, apolloShopEntity);
			} else {
				int rotateGroupID = rotateGroupShopList.get(0).getRotateGroupID();
				int apolloShopID = rotateGroupShopList.get(0).getShopID();
				int apolloShopExtendType = apolloShopExtendDAO.queryApolloShopExtendByShopIDAndBizID(apolloShopID, apolloShopExtend.getBizID()).get(0).getType();
				apolloShopExtend.setType(apolloShopExtendType);
				changeApolloShopExtendType(apolloShopExtend);
				changeRotateGroupType(rotateGroupID);//更新轮转组type
				insertRotateGroupShop(rotateGroupID, apolloShopEntity);
			}
		}
	}

	@Override
	public void updateShop(int shopId) {
		ShopDTO shopDTO = shopService.loadShop(shopId);
		if (shopDTO == null)
			throw new WrongShopInfoException("update shop info failed with wrong shopId!");

		List<ShopCategoryDTO> shopCategoryDTOList = shopService.findShopCategories(shopId, shopDTO.getCityId());
		List<ShopRegionDTO> shopRegionDTOList = shopService.findShopRegions(shopId);

		ApolloShopEntity apolloShopEntity = apolloShopDAO.queryApolloShopByShopIDWithNoStatus(shopId);
		boolean isStatusChanged = (apolloShopEntity.getShopStatus() != shopDTO.getPower());
		if (apolloShopEntity.getShopGroupID() != shopDTO.getShopGroupId()) {
			updateRotateGroupShopByShopID(shopId, shopDTO.getShopGroupId());
			isStatusChanged = true;
		}

		updateApolloShop(apolloShopEntity, shopDTO);

		//如果前台想要去掉Category和Region时，无法做到
		if (CollectionUtils.isNotEmpty(shopCategoryDTOList))
			updateShopCategoryList(shopCategoryDTOList, shopDTO);
		if (CollectionUtils.isNotEmpty(shopRegionDTOList))
			updateShopRegionList(shopRegionDTOList, shopDTO);

		if (isStatusChanged) {
			updateRotateGroupTypeAndStatusByShopID(shopId);
		}
	}

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
		for (RotateGroupShopEntity group : rotateGroupShopDAO.queryRotateGroupShopByShopID(shopId)) {
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
				rotateGroup.setType(RotateGroupTypeEnum.CHAIN.getCode());
			} else {
				// 单店
				rotateGroup.setType(RotateGroupTypeEnum.SINGLE.getCode());
			}
		} else {
			// 没有门店，设为无效
			rotateGroup.setStatus(0);
		}

		rotateGroupDAO.updateRotateGroup(rotateGroup);
	}

	@Override
	public void updateRotateGroupTypeAndStatus(int rotateGroupID) {
		RotateGroupEntity rotateGroup = rotateGroupDAO.getRotateGroupIgnoreStatus(rotateGroupID);
		RotateGroupEntity oldRotateGroup = rotateGroup;
		// 如果没有这个轮转组，就不操作
		if (rotateGroup == null) {
			return;
		}

		boolean flag = false;
		int shopCountInThisRotateGroup = rotateGroupShopDAO.getShopNumInGroup(rotateGroupID);
		int rotateGroupStatus = rotateGroup.getStatus();
		int rotateGroupType = rotateGroup.getType();

		if (shopCountInThisRotateGroup > 0 && rotateGroupStatus != 1) {
			// 有门店,设为有效
			rotateGroup.setStatus(1);
			flag = true;
		}
		if (shopCountInThisRotateGroup == 0 && rotateGroupStatus != 0) {
			// 没有门店，设为无效
			rotateGroup.setStatus(0);
			flag = true;
		}
		if (shopCountInThisRotateGroup > 1 && rotateGroupType != 1) {
			// 大于1家门店,设为连锁店
			rotateGroup.setType(RotateGroupTypeEnum.CHAIN.getCode());
			flag = true;
		}
		if (shopCountInThisRotateGroup <= 1 && rotateGroupType != 0) {
			// 单店
			rotateGroup.setType(RotateGroupTypeEnum.SINGLE.getCode());
			flag = true;
		}

		if (flag) {
			rotateGroupDAO.updateRotateGroup(rotateGroup);
		}
	}

	@Override
	public List<Integer> getRotateGroupIDList(int pageSize, int offset) {
		return rotateGroupDAO.queryRotateGroupIDList(pageSize, offset);
	}

	@Override
	public int getRotateGroupNum() {
		return rotateGroupDAO.getRotateGroupNum();
	}

	@Override
	public void updateShopExtendTypeByRotateGroupID(int rotateGroupID) {
		int vipShopNum = apolloShopExtendDAO.queryVipShopExtendNumByRotateGroupID(rotateGroupID);
		if (vipShopNum > 0)
			apolloShopExtendDAO.updateApolloShopExtendTypeByRotateGroupID(rotateGroupID);
	}

	private void insertShopRegionList(List<ShopRegionDTO> shopRegionDTOList, ShopDTO shopDTO) {
		List<ShopRegionEntity> shopRegionEntityList = Lists.newArrayList();
		int mainRegionId = shopDTO.getMainRegionId();
		int shopId = shopDTO.getShopId();
		for (ShopRegionDTO shopRegionDTO : shopRegionDTOList) {
			ShopRegionEntity shopRegionEntity = new ShopRegionEntity();
			shopRegionEntity.setShopID(shopId);
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

	public int insertRotateGroup(ApolloShopExtendEntity apolloShopExtend) {
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
				rotateGroupEntity.setType(RotateGroupTypeEnum.SINGLE.getCode());//0：单店；1：连锁店
				rotateGroupEntity.setStatus(1);
				int rotateGroupID = rotateGroupDAO.addToRotateGroup(rotateGroupEntity);
				rotateGroupIDList.add(rotateGroupID);
			}
		}
		return rotateGroupIDList;
	}

	private void changeRotateGroupType(int rotateGroupID) {
		RotateGroupEntity rotateGroupEntity = rotateGroupDAO.getRotateGroup(rotateGroupID);
		rotateGroupEntity.setType(RotateGroupTypeEnum.CHAIN.getCode());//0：单店；1：连锁店
		rotateGroupDAO.updateRotateGroup(rotateGroupEntity);
	}

	private void changeApolloShopExtendType(ApolloShopExtendEntity apolloShopExtend) {
		apolloShopExtendDAO.updateApolloShopExtend(apolloShopExtend);
	}

	private ApolloShopEntity insertApolloShop(ShopDTO shopDTO) {
		ApolloShopEntity apolloShopEntity = new ApolloShopEntity();
		apolloShopEntity.setShopID(shopDTO.getShopId());
		apolloShopEntity.setShopGroupID(shopDTO.getShopGroupId());
		apolloShopEntity.setCityID(shopDTO.getCityId());
		apolloShopEntity.setDistrict(shopDTO.getDistrict());
		apolloShopEntity.setShopType(shopDTO.getShopType());
		apolloShopEntity.setShopStatus(getApolloShopStatus(shopDTO.getPower(), shopDTO.getDisplayStatus()));
		apolloShopEntity.setProvinceID(cityService.loadCity(shopDTO.getCityId()).getProvinceID());

		int id = apolloShopDAO.addApolloShop(apolloShopEntity);
		apolloShopEntity.setId(id);
		return apolloShopEntity;
	}

	/**
	 * 根据门店的自然状态和审核状态返回门店的最终状态
	 * displayStatus=1&&businessStatus=x   ======> businessStatus = x
	 * displayStatus=0&&businessStatus=x   ======> businessStatus = 2
	 * displayStatus=2&&businessStatus=x   ======> businessStatus = 1
	 *
	 * @param businessStatus 门店的自然状态
	 * @param displayStatus  门店的审核状态
	 * @return
	 */
	private int getApolloShopStatus(Short businessStatus, Short displayStatus) {
		if (displayStatus == 0)
			return 2;
		if (displayStatus == 1)
			return businessStatus;
		return 1;
	}

	/**
	 * 按biz为门店创建shopExtend
	 *
	 * @param shopID
	 * @return
	 */
	private List<ApolloShopExtendEntity> insertApolloShopExtendList(int shopID) {
		List<ApolloShopExtendEntity> extendEntities = Lists.newArrayList();
		for (ApolloShopExtendFactory factory : extendFactories) {
			extendEntities.add(factory.createApolloShopExtend(shopID));
		}
		apolloShopExtendDAO.addApolloShopExtendByList(extendEntities);
		return extendEntities;
	}

	private void updateApolloShop(ApolloShopEntity apolloShopEntity, ShopDTO shopDTO) {
		apolloShopEntity.setShopGroupID(shopDTO.getShopGroupId());
		apolloShopEntity.setShopType(shopDTO.getShopType());
		apolloShopEntity.setCityID(shopDTO.getCityId());
		apolloShopEntity.setDistrict(shopDTO.getDistrict());
		apolloShopEntity.setShopStatus(getApolloShopStatus(shopDTO.getPower(), shopDTO.getDisplayStatus()));
		apolloShopEntity.setProvinceID(cityService.loadCity(shopDTO.getCityId()).getProvinceID());

		apolloShopDAO.updateApolloShop(apolloShopEntity);
	}

	private void updateShopRegionList(List<ShopRegionDTO> shopRegionDTOList, ShopDTO shopDTO) {
		shopRegionDAO.deleteShopRegionDirectlyByShopID(shopDTO.getShopId());
		insertShopRegionList(shopRegionDTOList, shopDTO);
	}

	private void updateShopCategoryList(List<ShopCategoryDTO> shopCategoryDTOList, ShopDTO shopDTO) {
		shopCategoryDAO.deleteShopCategoryDirectlyByShopID(shopDTO.getShopId());
		insertShopCategoryList(shopCategoryDTOList, shopDTO);
	}


	/**
	 * 对于合并连锁店：
	 * 1.如果被变化的门店在公海中，则将被变化门店的rotateGroupID改成同一shopGroup下最小的rotateGroupID
	 * 2.如果被变化的门店在私海中，则遍历同一shopGroup，将rotateGroupID最小的且属于公海的门店的rotateGroupID改成被变化门店的rotateGroupID
	 * 2.1 同一shopGroup下左右门店都在私海则不合并
	 * 拆分连锁店不需要以上操作
	 *
	 * @param shopId      变化的门店ID
	 * @param shopGroupId 变化后门店的shopGroupID
	 */
	private void updateRotateGroupShopByShopID(int shopId, int shopGroupId) {
		for (BizTypeEnum bizTypeEnum : BizTypeEnum.values()) {
			int bizId = bizTypeEnum.getCode();
			List<RotateGroupShopEntity> rotateGroupShopEntities = rotateGroupShopDAO.queryRotateGroupShopByShopGroupIDAndBizID(shopGroupId, bizId);
			RotateGroupShopEntity changedRotateGroupShop = rotateGroupShopDAO.queryRotateGroupSHopByShopIDAndBizID(shopId, bizId);
			if (CollectionUtils.isNotEmpty(rotateGroupShopEntities)) {
				if (rotateGroupUserService.findByShopIdAndBizId(shopId, bizId) == null) {//门店在公海里
					int rotateGroupID = rotateGroupShopEntities.get(0).getRotateGroupID();
					changedRotateGroupShop.setRotateGroupID(rotateGroupID);
				} else {//门店在私海里
					for (RotateGroupShopEntity rotateGroupShop : rotateGroupShopEntities) {
						if (rotateGroupUserService.findByRotateGroupId(rotateGroupShop.getRotateGroupID()) == null) {
							rotateGroupShop.setRotateGroupID(changedRotateGroupShop.getRotateGroupID());
							rotateGroupShopDAO.updateRotateGroupShop(rotateGroupShop);
						}
					}
				}
			}
			changedRotateGroupShop.setShopGroupID(shopGroupId);
			rotateGroupShopDAO.updateRotateGroupShop(changedRotateGroupShop);
		}
	}

}
