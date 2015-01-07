package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.RotateGroupShopService;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.dto.RotateGroupShopDTO;
import com.dianping.rotate.shop.entity.RotateGroupShopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
@Service("RotateGroupShopService")
public class RotateGroupShopServiceImpl implements RotateGroupShopService {
	@Autowired
	RotateGroupShopDAO rotateGroupShopDAO;

	@Override
	public RotateGroupShopDTO getRotateGroupShop(int rotateGroupID) {
		RotateGroupShopDTO rotateGroupShopDTO = new RotateGroupShopDTO();
		List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);
		if(rotateGroupShopEntityList != null && rotateGroupShopEntityList.size() != 0){
			RotateGroupShopEntity rotateGroupShopEntity = rotateGroupShopEntityList.get(0);
			rotateGroupShopDTO.setRotateGroupID(rotateGroupShopEntity.getRotateGroupID());
			rotateGroupShopDTO.setShopID(rotateGroupShopEntity.getShopID());
			rotateGroupShopDTO.setShopGroupID(rotateGroupShopEntity.getShopGroupID());
		}
		return rotateGroupShopDTO;
	}
}
