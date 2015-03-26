package com.dianping.rotate.shop.impl;

import com.dianping.apollobase.api.DepartmentGroupService;
import com.dianping.apollobase.api.GroupBusiness;
import com.dianping.rotate.shop.api.RotateGroupService;
import com.dianping.rotate.shop.constants.*;
import com.dianping.rotate.shop.dao.ApolloShopBusinessStatusDAO;
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO;
import com.dianping.rotate.shop.dao.RotateGroupDAO;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.dto.RotateGroupDTO;
import com.dianping.rotate.shop.exceptions.RequestServiceException;
import com.dianping.rotate.shop.json.ApolloShopBusinessStatusEntity;
import com.dianping.rotate.shop.json.ApolloShopExtendEntity;
import com.dianping.rotate.shop.json.RotateGroupEntity;
import com.dianping.rotate.shop.json.RotateGroupShopEntity;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
@Service("RotateGroupService")
public class RotateGroupServiceImpl implements RotateGroupService {
	private final static int MAX_RESULT_SIZE = 10000;

	@Autowired
	RotateGroupDAO rotateGroupDAO;

	@Autowired
	RotateGroupShopDAO rotateGroupShopDAO;

	@Autowired
	ApolloShopBusinessStatusDAO apolloShopBusinessStatusDAO;

	@Autowired
	ApolloShopExtendDAO apolloShopExtendDAO;

	@Autowired
	DepartmentGroupService departmentGroupService;

	private Function<RotateGroupEntity, RotateGroupDTO> toRotateGroupDTO = new Function<RotateGroupEntity, RotateGroupDTO>() {
		@Override
		public RotateGroupDTO apply(RotateGroupEntity from) {
			RotateGroupDTO to = null;
			if(from != null){
				to = new RotateGroupDTO();
				to.setId(from.getId());
				to.setBizID(from.getBizID());
				to.setType(from.getType());
				to.setStatus(from.getStatus());
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

	@Override
	public boolean mergeRotateGroup(int rotateGroupID, List<Integer> rotateGroupIDList) {
		if(CollectionUtils.isNotEmpty(rotateGroupIDList)) {
			if(rotateGroupIDList.size() > MAX_RESULT_SIZE) {
				throw new RequestServiceException("RotateGroupIDs are too many!");
			}
			RotateGroupEntity rotateGroupEntity = getRotateGroupEntity(rotateGroupID);
			if(rotateGroupEntity != null) {
				rotateGroupDAO.deleteRotateGroupBatch(rotateGroupIDList);
				updateShopExtendType(rotateGroupID, rotateGroupEntity, rotateGroupIDList);
				rotateGroupShopDAO.updateRotateGroupShopRotateGroupIDBatch(rotateGroupID, rotateGroupIDList);
				return true;
			}
		}
		return false;
	}

	@Override
	public RotateGroupDTO createRotateGroup(int shopGroupId,int bizID, List<Integer> apolloShopIDList) {
		removeDuplicate(apolloShopIDList);
		RotateGroupEntity rotateGroupEntity = addRotateGroup(bizID, apolloShopIDList);
		if(rotateGroupEntity != null) {
			addRotateGroupShop(rotateGroupEntity.getId(), apolloShopIDList,shopGroupId);
			return toRotateGroupDTO.apply(rotateGroupEntity);
		}
		return null;
	}

	@Override
	public boolean deleteRotateGroupByRotateGroupID(int rotateGroupID) {
		rotateGroupDAO.deleteRotateGroup(rotateGroupID);
		rotateGroupShopDAO.deleteRotateGroupShopByRotateGroupID(rotateGroupID);
		return true;
	}

	@Override
	public RotateGroupDTO createRotateGroup(int bizID) {
		RotateGroupEntity rotateGroupEntity = new RotateGroupEntity();
		rotateGroupEntity.setBizID(bizID);
		rotateGroupEntity.setType(RotateGroupTypeEnum.SINGLE.getCode());
		rotateGroupEntity.setStatus(1);
		rotateGroupEntity.setId(rotateGroupDAO.addToRotateGroup(rotateGroupEntity));
		return toRotateGroupDTO.apply(rotateGroupEntity);
	}

	@Override
	public void updateType(int rotateGroupID, int type) {
		rotateGroupDAO.updateRotateGroupTypeByID(rotateGroupID, type);
	}

	@Override
	public List<RotateGroupDTO> getRotateGroupList(int shopGroupID, int bizID) {
		List<RotateGroupDTO> rotateGroupDTOs =  Lists.newArrayList();
		for(RotateGroupDTO dto:
				Lists.transform(rotateGroupDAO.queryRotateGroupByShopGroupIDAndBizID(shopGroupID, bizID), toRotateGroupDTO)){
			if(dto != null){
				int rotateGroupID = dto.getId();
				processRotateShopDTOCooperationStatus(dto, rotateGroupID);
				processRotateShopDTOCustomerStatus(dto, rotateGroupID);
				rotateGroupDTOs.add(dto);
			}
		}
		return rotateGroupDTOs;
	}

    @Override
    public List<RotateGroupDTO> getRotateGroupWithNoStatus(int rotateGroupID){
        return Lists.transform(rotateGroupDAO.getRotateGroupWithNoStatus(rotateGroupID), toRotateGroupDTO);
    }

	@Override
	public void updateTypeUsedBySplitAndMerge(int rotateGroupID, int type) {
		rotateGroupDAO.updateRotateGroupTypeUsedBySplitAndMerge(rotateGroupID, type);
	}

	@Override
	public void deleteRotateGroupUsedBySplitAndMerge(int rotateGroupID) {
		rotateGroupDAO.deleteRotateGroupUsedBySplitAndMerge(rotateGroupID);
	}

	private RotateGroupEntity addRotateGroup(int bizID, List<Integer> apolloShopIDList) {
		RotateGroupEntity rotateGroupEntity = new RotateGroupEntity();
		rotateGroupEntity.setBizID(bizID);
		rotateGroupEntity.setType(getRotateGroupType(apolloShopIDList));
		rotateGroupEntity.setStatus(1);
		int rotateGroupID = rotateGroupDAO.addToRotateGroup(rotateGroupEntity);
		if(rotateGroupID < 0) {
			return null;
		}
		rotateGroupEntity.setId(rotateGroupID);
		return rotateGroupEntity;
	}

	private void addRotateGroupShop(int rotateGroupID, List<Integer> apolloShopIDList,int shopGroupId) {
		if(CollectionUtils.isNotEmpty(apolloShopIDList)) {
			List<RotateGroupShopEntity> rotateGroupShopEntityList = new ArrayList<RotateGroupShopEntity>();
			for(int shopID : apolloShopIDList) {
				RotateGroupShopEntity rotateGroupShopEntity = new RotateGroupShopEntity();
				rotateGroupShopEntity.setRotateGroupID(rotateGroupID);
				rotateGroupShopEntity.setShopID(shopID);
				rotateGroupShopEntity.setShopGroupID(shopGroupId);
				rotateGroupShopEntity.setStatus(1);
				rotateGroupShopEntityList.add(rotateGroupShopEntity);
			}
			rotateGroupShopDAO.addToRotateGroupShopByList(rotateGroupShopEntityList);
		}
	}

	private void removeDuplicate(List<Integer> apolloShopIDList) {
		HashSet hashSet = new HashSet(apolloShopIDList);
		apolloShopIDList.clear();
		apolloShopIDList.addAll(hashSet);
	}

	private int getRotateGroupType(List<Integer> apolloShopIDList) {
		int type = 0;
		if(CollectionUtils.isNotEmpty(apolloShopIDList)) {
			int apolloShopIDListSize = apolloShopIDList.size();
			if(apolloShopIDListSize > MAX_RESULT_SIZE) {
				throw new RequestServiceException("ApolloShopIDs are too many!");
			}
			if(apolloShopIDListSize > 1) {
				type = 1;
			}
		}
		return type;
	}

	private void updateShopExtendType(int rotateGroupID, RotateGroupEntity rotateGroupEntity, List<Integer> rotateGroupIDList) {
		List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);
		if(CollectionUtils.isNotEmpty(rotateGroupShopEntityList)) {
			int shopID = rotateGroupShopEntityList.get(0).getShopID();
			Integer bizID = rotateGroupEntity.getBizID();
			if(bizID != null) {
				ApolloShopExtendEntity apolloShopExtendEntity = getApolloShopExtendEntityByBizIDAndShopID(bizID, shopID);
				int type = apolloShopExtendEntity.getType();
				apolloShopExtendDAO.updateApolloShopExtendTypeByRotateGroupIDListAndType(rotateGroupIDList, type, bizID);
			}
		}
	}

	private void processRotateShopDTOCooperationStatus(RotateGroupDTO rotateGroupDTO, int rotateGroupID) {
		if(rotateGroupDTO != null) {
			if(rotateGroupDTO.getBizID() == BizTypeEnum.JH.getCode()) {
				rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.NO_COOP.getCode());
				return;
			}
			List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);
			List<Integer> shopIDList = getShopIDs(rotateGroupShopEntityList);
			if(CollectionUtils.isNotEmpty(shopIDList)) {
				List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList = apolloShopBusinessStatusDAO.queryApolloShopBusinessStatusByShopIDList(shopIDList);
				processRotateShopCooperationStatus(apolloShopBusinessStatusEntityList, rotateGroupDTO);
			// 轮转组未取到门店，轮转组状态设置为未知
			} else {
				rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.UNKNOW.getCode());
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
			int rotateGroupStatusIndexTemp = 1;
			List<Date> offlineTimeList = new ArrayList<Date>();
			List<Integer> businessTypeList = getBusinessTypeList(departmentGroupService.getGroupBusinesses(rotateGroupDTO.getBizID()));
			for(int i=0;i<apolloShopBusinessStatusEntityList.size();i++) {
				ApolloShopBusinessStatusEntity apolloShopBusinessStatusEntity = apolloShopBusinessStatusEntityList.get(i);
				if(businessTypeList.contains(apolloShopBusinessStatusEntity.getBusinessType())) {
					if(RotateShopStatusEnum.ONLINE.getCode() == apolloShopBusinessStatusEntity.getCooperationStatus()) {
						rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.COOPING.getCode());
						return;
					} else if(apolloShopBusinessStatusEntity.getOfflineDate() != null &&
							RotateShopStatusEnum.OFFLINE.getCode() == apolloShopBusinessStatusEntity.getCooperationStatus() && rotateGroupStatusIndexTemp <= 2) {
						rotateGroupStatusIndexTemp = 2;
						offlineTimeList.add(apolloShopBusinessStatusEntity.getOfflineDate());
					} else if(apolloShopBusinessStatusEntity.getOfflineDate() == null &&
							RotateShopStatusEnum.OFFLINE.getCode() == apolloShopBusinessStatusEntity.getCooperationStatus() && rotateGroupStatusIndexTemp < 1) {
						rotateGroupStatusIndexTemp = 1;
					}
				}
			}
			if(rotateGroupStatusIndexTemp == 2) {
				rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.COOP_BREAK.getCode());
				List<java.util.Date> dateList = getMinAndMaxOfflineTime(offlineTimeList);
				rotateGroupDTO.setMinOfflineTime(dateList.get(0));
				rotateGroupDTO.setMaxOfflineTime(dateList.get(1));
			} else if(rotateGroupStatusIndexTemp == 1) {
				rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.NO_COOP.getCode());
			}
		// 未取到shop status则认为未合作过
		} else {
			rotateGroupDTO.setCooperationStatus(RotateShopCooperationStatusEnum.NO_COOP.getCode());
		}
	}

	private List<Integer> getBusinessTypeList(List<GroupBusiness> groupBusinessList) {
		List<Integer> businessTypeList = new ArrayList<Integer>();
		if(CollectionUtils.isNotEmpty(groupBusinessList)) {
			for(GroupBusiness groupBusiness : groupBusinessList) {
				businessTypeList.add(groupBusiness.getBusinessId());
			}
		}
		return businessTypeList;
	}

	private List<java.util.Date> getMinAndMaxOfflineTime(List<Date> offlineTimeList) {
		List<java.util.Date> dateList = new ArrayList<java.util.Date>();
		dateList.add(offlineTimeList.get(offlineTimeList.size() - 1));
		dateList.add(offlineTimeList.get(0));
		return dateList;
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
		if(CollectionUtils.isEmpty(rotateGroupIDList)) {
			return Collections.emptyList();
		}
		if(rotateGroupIDList.size() > MAX_RESULT_SIZE)
			throw new RequestServiceException("RotateGroupIDs are too many!");
		List<RotateGroupEntity> rotateGroupEntityList = rotateGroupDAO.getRotateGroupList(rotateGroupIDList);
		if(CollectionUtils.isEmpty(rotateGroupEntityList))
			throw new RequestServiceException("rotateGroupEntityList does not exist or is null!");
		return rotateGroupEntityList;
	}

}
