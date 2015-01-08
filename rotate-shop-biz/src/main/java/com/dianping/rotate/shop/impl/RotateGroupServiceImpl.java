package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.RotateGroupService;
import com.dianping.rotate.shop.dao.BizDAO;
import com.dianping.rotate.shop.dao.RotateGroupDAO;
import com.dianping.rotate.shop.dto.RotateGroupDTO;
import com.dianping.rotate.shop.dto.RotateGroupExtendDTO;
import com.dianping.rotate.shop.entity.BizEntity;
import com.dianping.rotate.shop.entity.RotateGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	BizDAO bizDAO;

	@Override
	public RotateGroupDTO getRotateGroup(int rotateGroupID) {
		return transRotateGroupEntityToDTO(getRotateGroupEntity(rotateGroupID));
	}

	@Override
	public RotateGroupExtendDTO getRotateGroupExtend(int rotateGroupID) {
		RotateGroupEntity rotateGroupEntity = getRotateGroupEntity(rotateGroupID);
		if(rotateGroupEntity != null) {
			int bizID = rotateGroupEntity.getBizID();
			List<BizEntity> bizEntityList = bizDAO.queryBiz(bizID);

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
