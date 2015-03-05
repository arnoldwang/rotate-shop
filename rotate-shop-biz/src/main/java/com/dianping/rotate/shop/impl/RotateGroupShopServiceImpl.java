package com.dianping.rotate.shop.impl;

import com.beust.jcommander.internal.Maps;
import com.dianping.rotate.shop.api.RotateGroupShopService;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.dto.RotateGroupShopDTO;
import com.dianping.rotate.shop.json.RotateGroupShopEntity;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
@Service("RotateGroupShopService")
public class RotateGroupShopServiceImpl implements RotateGroupShopService {
	@Autowired
	RotateGroupShopDAO rotateGroupShopDAO;

	private Function<RotateGroupShopEntity, RotateGroupShopDTO> toRotateShopDTO = new Function<RotateGroupShopEntity, RotateGroupShopDTO>() {
		@Override
		public RotateGroupShopDTO apply(RotateGroupShopEntity from) {
			RotateGroupShopDTO to = null;
			if(from != null){
				to = new RotateGroupShopDTO();
				to.setRotateGroupID(from.getRotateGroupID());
				to.setShopID(from.getShopID());
				to.setShopGroupID(from.getShopGroupID());
			}
			return to;
		}
	};

	@Override
	public List<RotateGroupShopDTO> getRotateGroupShop(int rotateGroupID) {
		return Lists.transform(rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID), toRotateShopDTO);
	}

	@Override
	public Map<Integer, List<RotateGroupShopDTO>> getRotateGroupShop(List<Integer> rotateGroupIDs) {
		Map<Integer, List<RotateGroupShopDTO>> result = Maps.newHashMap();
		for(int rotateGroupID: rotateGroupIDs){
			result.put(rotateGroupID,
					Lists.transform(rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupID), toRotateShopDTO));
		}
		return result;
	}

	@Override
	public List<RotateGroupShopDTO> getRotateGroupShopByShopGroupIDAndBizID(int shopGroupID, int bizID) {
		return Lists.transform(rotateGroupShopDAO.queryRotateGroupShopByShopGroupIDAndBizID(shopGroupID, bizID), toRotateShopDTO);
	}
}
