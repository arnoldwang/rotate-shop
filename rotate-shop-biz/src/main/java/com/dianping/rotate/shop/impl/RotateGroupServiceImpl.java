package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.RotateGroupService;
import com.dianping.rotate.shop.constants.ApolloShopTypeEnum;
import com.dianping.rotate.shop.constants.RotateGroupCustomerStatusEnum;
import com.dianping.rotate.shop.constants.RotateShopCooperationStatusEnum;
import com.dianping.rotate.shop.constants.RotateShopStatusEnum;
import com.dianping.rotate.shop.dao.ApolloShopBusinessStatusDAO;
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO;
import com.dianping.rotate.shop.dao.RotateGroupDAO;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.dto.RotateGroupDTO;
import com.dianping.rotate.shop.entity.ApolloShopBusinessStatusEntity;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;
import com.dianping.rotate.shop.entity.RotateGroupEntity;
import com.dianping.rotate.shop.entity.RotateGroupShopEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
@Service("RotateGroupService")
public class RotateGroupServiceImpl implements RotateGroupService {
	@Autowired
	RotateGroupDAO rotateGroupDAO;

	@Autowired
	RotateGroupShopDAO rotateGroupShopDAO;

	@Autowired
	ApolloShopBusinessStatusDAO apolloShopBusinessStatusDAO;

	@Autowired
	ApolloShopExtendDAO apolloShopExtendDAO;

	@Override
	public RotateGroupDTO getRotateGroup(int rotateGroupID) {
		return transRotateGroupEntityToDTO(getRotateGroupEntity(rotateGroupID));
	}

	@Override
	public RotateGroupDTO getRotateGroupWithCooperationStatus(int rotateGroupID) {
		RotateGroupDTO rotateGroupDTO = transRotateGroupEntityToDTO(getRotateGroupEntity(rotateGroupID));
		List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);
		List<Integer> shopIDList = getShopIDs(rotateGroupShopEntityList);
		if(CollectionUtils.isNotEmpty(shopIDList)) {
			List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList = apolloShopBusinessStatusDAO.queryApolloShopBusinessStatusByShopIDList(shopIDList);
			processRotateShopCooperationStatus(apolloShopBusinessStatusEntityList, rotateGroupDTO);
		}
		return rotateGroupDTO;
	}

	@Override
	public RotateGroupDTO getRotateGroupWithCustomerStatus(int bizID, int shopID) {
		RotateGroupDTO rotateGroupDTO = transRotateGroupEntityToDTO(getRotateGroupEntityByBizIDAndShopID(bizID, shopID));
		ApolloShopExtendEntity apolloShopExtendEntity = getApolloShopExtendEntityByBizIDAndShopID(bizID, shopID);
		processRotateShopCustomerStatus(rotateGroupDTO, apolloShopExtendEntity);
		return rotateGroupDTO;
	}

	private RotateGroupEntity getRotateGroupEntityByBizIDAndShopID(int bizID, int shopID) {
		List<RotateGroupEntity> rotateGroupEntityList = rotateGroupDAO.queryRotateGroupByBizIDAndShopID(bizID, shopID);
		if(CollectionUtils.isNotEmpty(rotateGroupEntityList)){
			RotateGroupEntity rotateGroupEntity = rotateGroupEntityList.get(0);
			return rotateGroupEntity;
		}
		return null;
	}

	private ApolloShopExtendEntity getApolloShopExtendEntityByBizIDAndShopID(int bizID, int shopID) {
		List<ApolloShopExtendEntity> apolloShopExtendEntityList = apolloShopExtendDAO.queryApolloShopExtendByShopIDAndBizID(shopID, bizID);;
		if(CollectionUtils.isNotEmpty(apolloShopExtendEntityList)){
			ApolloShopExtendEntity apolloShopExtendEntity = apolloShopExtendEntityList.get(0);
			return apolloShopExtendEntity;
		}
		return null;
	}

	private void processRotateShopCustomerStatus(RotateGroupDTO rotateGroupDTO, ApolloShopExtendEntity apolloShopExtendEntity) {
		if(apolloShopExtendEntity != null) {
			int shopCustomerStatus = apolloShopExtendEntity.getType();
			if(ApolloShopTypeEnum.VIP.getCode() == shopCustomerStatus) {
				rotateGroupDTO.setCustomerStatus(RotateGroupCustomerStatusEnum.VIP.getCode());
			} else if(ApolloShopTypeEnum.COMMON.getCode() == shopCustomerStatus) {
				rotateGroupDTO.setCustomerStatus(RotateGroupCustomerStatusEnum.COMMON.getCode());
			}
		}
	}

	private void processRotateShopCooperationStatus(List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList, RotateGroupDTO rotateGroupDTO) {
		if(CollectionUtils.isNotEmpty(apolloShopBusinessStatusEntityList)) {
			int rotateGroupStatusIndexTemp = 0;
			for(int i=0;i<apolloShopBusinessStatusEntityList.size();i++) {
				ApolloShopBusinessStatusEntity apolloShopBusinessStatusEntity = apolloShopBusinessStatusEntityList.get(i);
				if(apolloShopBusinessStatusEntity.getOfflineDate() == null &&
						RotateShopStatusEnum.ONLINE.getCode() == apolloShopBusinessStatusEntity.getCooperationStatus()) {
					rotateGroupStatusIndexTemp = 3;
					rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.COOPING.getCode());
					return;
				} else if(apolloShopBusinessStatusEntity.getOfflineDate() == null &&
						RotateShopStatusEnum.OFFLINE.getCode() == apolloShopBusinessStatusEntity.getCooperationStatus() && rotateGroupStatusIndexTemp < 2) {
					rotateGroupStatusIndexTemp = 2;
				} else if(apolloShopBusinessStatusEntity.getOfflineDate() != null && rotateGroupStatusIndexTemp < 1) {
					rotateGroupStatusIndexTemp = 1;
				}
			}
			if(rotateGroupStatusIndexTemp == 1) {
				rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.COOP_BREAK.getCode());
			} else if(rotateGroupStatusIndexTemp == 2) {
				rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.NO_COOP.getCode());
			}
		}
	}

	private List<Integer> getShopIDs(List<RotateGroupShopEntity> rotateGroupShopEntityList) {
		if(CollectionUtils.isNotEmpty(rotateGroupShopEntityList)) {
			List<Integer> shopIDList = new ArrayList<Integer>();
			for(RotateGroupShopEntity rotateGroupShopEntity : rotateGroupShopEntityList) {
				int shopID = rotateGroupShopEntity.getShopID();
				if(!shopIDList.contains(shopID)) {
					shopIDList.add(shopID);
				}
			}
			return shopIDList;
		}
		return null;
	}

	private RotateGroupEntity getRotateGroupEntity(int rotateGroupID) {
		List<RotateGroupEntity> rotateGroupEntityList = rotateGroupDAO.queryRotateGroup(rotateGroupID);
		if(CollectionUtils.isNotEmpty(rotateGroupEntityList)){
			RotateGroupEntity rotateGroupEntity = rotateGroupEntityList.get(0);
			return rotateGroupEntity;
		}
		return null;
	}

	private RotateGroupDTO transRotateGroupEntityToDTO(RotateGroupEntity rotateGroupEntity) {
		RotateGroupDTO rotateGroupDTO = new RotateGroupDTO();
		if(rotateGroupEntity != null) {
			rotateGroupDTO.setId(rotateGroupEntity.getId());
			rotateGroupDTO.setBizID(rotateGroupEntity.getBizID());
			rotateGroupDTO.setType(rotateGroupEntity.getType());
		}
		return rotateGroupDTO;
	}

}
