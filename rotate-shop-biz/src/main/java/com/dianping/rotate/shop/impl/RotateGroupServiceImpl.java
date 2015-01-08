package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.RotateGroupService;
import com.dianping.rotate.shop.constants.RotateShopCooperationStatusEnum;
import com.dianping.rotate.shop.dao.ApolloShopBusinessStatusDAO;
import com.dianping.rotate.shop.dao.RotateGroupDAO;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.dto.RotateGroupDTO;
import com.dianping.rotate.shop.dto.RotateGroupExtendDTO;
import com.dianping.rotate.shop.entity.ApolloShopBusinessStatusEntity;
import com.dianping.rotate.shop.entity.RotateGroupEntity;
import com.dianping.rotate.shop.entity.RotateGroupShopEntity;
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

	@Override
	public RotateGroupDTO getRotateGroup(int rotateGroupID) {
		return transRotateGroupEntityToDTO(getRotateGroupEntity(rotateGroupID));
	}

	@Override
	public RotateGroupExtendDTO getRotateGroupExtend(int rotateGroupID) {
		RotateGroupExtendDTO rotateGroupExtendDTO = new RotateGroupExtendDTO();
		List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);
		List<Integer> shopIDList = getShopIDs(rotateGroupShopEntityList);
		if(shopIDList != null && shopIDList.size() != 0) {
			List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList = apolloShopBusinessStatusDAO.queryApolloShopBusinessStatusByShopIDList(shopIDList);
			processRotateShopCooperationStatus(apolloShopBusinessStatusEntityList, rotateGroupExtendDTO);
		}
		return rotateGroupExtendDTO;
	}

	private void processRotateShopCooperationStatus(List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList, RotateGroupExtendDTO rotateGroupExtendDTO) {
		if(apolloShopBusinessStatusEntityList != null && apolloShopBusinessStatusEntityList.size() != 0) {
			for(int i=0;i<apolloShopBusinessStatusEntityList.size();i++) {
				if(RotateShopCooperationStatusEnum.ONLINE.getCode() == apolloShopBusinessStatusEntityList.get(i).getCooperationStatus()) {
					rotateGroupExtendDTO.setStatus(RotateShopCooperationStatusEnum.ONLINE.getCode());
					return;
				}
			}
			rotateGroupExtendDTO.setStatus(RotateShopCooperationStatusEnum.OFFLINE.getCode());
		}
	}

	private List<Integer> getShopIDs(List<RotateGroupShopEntity> rotateGroupShopEntityList) {
		if(rotateGroupShopEntityList != null) {
			List<Integer> shopIDList = new ArrayList<Integer>();
			for(int i=0;i<rotateGroupShopEntityList.size();i++) {
				int shopID = rotateGroupShopEntityList.get(i).getShopID();
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
		if(rotateGroupEntityList != null && rotateGroupEntityList.size() != 0){
			RotateGroupEntity rotateGroupEntity = rotateGroupEntityList.get(0);
			return rotateGroupEntity;
		}
		return null;
	}

	private RotateGroupDTO transRotateGroupEntityToDTO(RotateGroupEntity rotateGroupEntity) {
		RotateGroupDTO rotateGroupDTO = new RotateGroupDTO();
		if(rotateGroupEntity != null) {
			rotateGroupDTO.setBizID(rotateGroupEntity.getBizID());
			rotateGroupDTO.setType(rotateGroupEntity.getType());
		}
		return rotateGroupDTO;
	}

}
