package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.RotateGroupService;
import com.dianping.rotate.shop.dao.RotateGroupDAO;
import com.dianping.rotate.shop.dto.RotateGroupDTO;
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

	@Override
	public RotateGroupDTO getRotateGroup(int rotateGroupID) {
		RotateGroupDTO rotateGroupDTO = new RotateGroupDTO();
		List<RotateGroupEntity> rotateGroupEntityList = rotateGroupDAO.queryRotateGroup(rotateGroupID);
		if(rotateGroupEntityList != null && rotateGroupEntityList.size() != 0){
			RotateGroupEntity rotateGroupEntity = rotateGroupEntityList.get(0);
			rotateGroupDTO.setBizID(rotateGroupEntity.getBizID());
			rotateGroupDTO.setType(rotateGroupEntity.getType());
		}
		return rotateGroupDTO;
	}
}
