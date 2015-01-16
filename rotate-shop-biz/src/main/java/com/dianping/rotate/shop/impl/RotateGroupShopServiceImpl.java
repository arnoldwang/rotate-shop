package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.RotateGroupShopService;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.dto.RotateGroupShopDTO;
import com.dianping.rotate.shop.json.RotateGroupShopEntity;
import org.apache.commons.collections.CollectionUtils;
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
		RotateGroupShopDTO rotateGroupShopDTO = null;
		List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID);
		if(CollectionUtils.isNotEmpty(rotateGroupShopEntityList)){
			RotateGroupShopEntity rotateGroupShopEntity = rotateGroupShopEntityList.get(0);
			rotateGroupShopDTO = new RotateGroupShopDTO();
			rotateGroupShopDTO.setRotateGroupID(rotateGroupShopEntity.getRotateGroupID());
			rotateGroupShopDTO.setShopID(rotateGroupShopEntity.getShopID());
			rotateGroupShopDTO.setShopGroupID(rotateGroupShopEntity.getShopGroupID());
		}
		return rotateGroupShopDTO;
	}
}
