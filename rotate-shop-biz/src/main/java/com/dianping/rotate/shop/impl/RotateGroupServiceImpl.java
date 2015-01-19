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
import com.dianping.rotate.shop.json.ApolloShopBusinessStatusEntity;
import com.dianping.rotate.shop.json.ApolloShopExtendEntity;
import com.dianping.rotate.shop.json.RotateGroupEntity;
import com.dianping.rotate.shop.json.RotateGroupShopEntity;
import com.dianping.rotate.shop.utils.CommonUtil;
import com.dianping.rotate.shop.exceptions.RequestServiceException;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

	private Function<RotateGroupEntity, RotateGroupDTO> toRotateGroupDTO = new Function<RotateGroupEntity, RotateGroupDTO>() {
		@Override
		public RotateGroupDTO apply(RotateGroupEntity from) {
			RotateGroupDTO to = null;
			if(from != null){
				to = new RotateGroupDTO();
				to.setId(from.getId());
				to.setBizID(from.getBizID());
				to.setType(from.getType());
			}
			return to;
		}
	};

	@Override
	public RotateGroupDTO getRotateGroup(int rotateGroupID) {
		RotateGroupDTO rotateGroupDTO = toRotateGroupDTO.apply(getRotateGroupEntity(rotateGroupID));
		processRotateShopDTOCooperationStatus(rotateGroupDTO, rotateGroupID);
		processRotateShopDTOCustomerStatus(rotateGroupDTO, rotateGroupID);
		return rotateGroupDTO;
	}

	@Override
	public RotateGroupDTO getRotateGroup(int bizID, int shopID) {
		RotateGroupDTO rotateGroupDTO = toRotateGroupDTO.apply(getRotateGroupEntityByBizIDAndShopID(bizID, shopID));
		if(rotateGroupDTO != null) {
			int rotateGroupID = rotateGroupDTO.getId();
			processRotateShopDTOCooperationStatus(rotateGroupDTO, rotateGroupID);
			processRotateShopDTOCustomerStatus(rotateGroupDTO, rotateGroupID);
		}
		return rotateGroupDTO;
	}

	@Override
	public List<RotateGroupDTO> getRotateGroup(List<Integer> rotateGroupIDList) {
		return Lists.transform(getRotateGroupEntity(rotateGroupIDList), toRotateGroupDTO);

	}

	@Override
	public RotateGroupDTO getRotateGroupWithCooperationStatus(int rotateGroupID) {
		RotateGroupDTO rotateGroupDTO = toRotateGroupDTO.apply(getRotateGroupEntity(rotateGroupID));
		processRotateShopDTOCooperationStatus(rotateGroupDTO, rotateGroupID);
		return rotateGroupDTO;
	}

	@Override
	public RotateGroupDTO getRotateGroupWithCustomerStatus(int rotateGroupID) {
		RotateGroupDTO rotateGroupDTO = toRotateGroupDTO.apply(getRotateGroupEntity(rotateGroupID));
		processRotateShopDTOCustomerStatus(rotateGroupDTO, rotateGroupID);
		return rotateGroupDTO;
	}

	private void processRotateShopDTOCooperationStatus(RotateGroupDTO rotateGroupDTO, int rotateGroupID) {
		if(rotateGroupDTO == null) {
			List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);
			List<Integer> shopIDList = getShopIDs(rotateGroupShopEntityList);
			if(CollectionUtils.isNotEmpty(shopIDList)) {
				List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList = apolloShopBusinessStatusDAO.queryApolloShopBusinessStatusByShopIDList(shopIDList);
				processRotateShopCooperationStatus(apolloShopBusinessStatusEntityList, rotateGroupDTO);
			}
		}
	}

	private void processRotateShopDTOCustomerStatus(RotateGroupDTO rotateGroupDTO, int rotateGroupID) {
		if(rotateGroupDTO != null) {
			List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);
			if(CollectionUtils.isNotEmpty(rotateGroupShopEntityList)) {
				int shopID = rotateGroupShopEntityList.get(0).getShopID();
				Integer bizID = rotateGroupDTO.getBizID();
				if(bizID != null) {
					ApolloShopExtendEntity apolloShopExtendEntity = getApolloShopExtendEntityByBizIDAndShopID(bizID, shopID);
					processRotateShopCustomerStatus(rotateGroupDTO, apolloShopExtendEntity);
				}
			}
		}
	}

	private RotateGroupEntity getRotateGroupEntityByBizIDAndShopID(int bizID, int shopID) {
		List<RotateGroupEntity> rotateGroupEntityList = rotateGroupDAO.queryRotateGroupByBizIDAndShopID(bizID, shopID);
		if(CollectionUtils.isNotEmpty(rotateGroupEntityList)){
			return rotateGroupEntityList.get(0);
		}
		return null;
	}

	private ApolloShopExtendEntity getApolloShopExtendEntityByBizIDAndShopID(int bizID, int shopID) {
		List<ApolloShopExtendEntity> apolloShopExtendEntityList = apolloShopExtendDAO.queryApolloShopExtendByShopIDAndBizID(shopID, bizID);;
		if(CollectionUtils.isNotEmpty(apolloShopExtendEntityList)){
			return apolloShopExtendEntityList.get(0);
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
			List<Date> offlineTimeList = new ArrayList<Date>();
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
				} else if(apolloShopBusinessStatusEntity.getOfflineDate() != null && rotateGroupStatusIndexTemp <= 1) {
					rotateGroupStatusIndexTemp = 1;
					offlineTimeList.add(apolloShopBusinessStatusEntity.getOfflineDate());
				}
			}
			if(rotateGroupStatusIndexTemp == 1) {
				rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.COOP_BREAK.getCode());
				List<java.util.Date> dateList = getMinAndMaxOfflineTime(offlineTimeList);
				rotateGroupDTO.setMinOfflineTime(dateList.get(0));
				rotateGroupDTO.setMaxOfflineTime(dateList.get(1));
			} else if(rotateGroupStatusIndexTemp == 2) {
				rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.NO_COOP.getCode());
			}
		}
	}

	private List<java.util.Date> getMinAndMaxOfflineTime(List<Date> offlineTimeList) {
		List<java.util.Date> dateList = new ArrayList<java.util.Date>();
		dateList.add(formatDate(offlineTimeList.get(offlineTimeList.size() - 1)));
		dateList.add(formatDate(offlineTimeList.get(0)));
		return dateList;
	}

	private java.util.Date formatDate(Date date) {
		CommonUtil commonUtil = new CommonUtil();
		String dateString = commonUtil.datetimeToString(new java.util.Date(date.getTime()));
		return commonUtil.stringToDateTime(dateString);
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
		return rotateGroupDAO.getRotateGroup(rotateGroupID);
	}


	private List<RotateGroupEntity> getRotateGroupEntity(List<Integer> rotateGroupIDList) {
		return rotateGroupDAO.getRotateGroupList(rotateGroupIDList);
	}

}
