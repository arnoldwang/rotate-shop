package com.dianping.rotate.shop.impl;

import com.beust.jcommander.internal.Maps;
import com.dianping.rotate.shop.api.RotateGroupShopService;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.dto.RotateGroupShopDTO;
import com.dianping.rotate.shop.json.RotateGroupShopEntity;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
			if (from != null) {
				to = new RotateGroupShopDTO();
				to.setId(from.getId());
				to.setRotateGroupID(from.getRotateGroupID());
				to.setShopID(from.getShopID());
				to.setShopGroupID(from.getShopGroupID());
				to.setStatus(from.getStatus());
			}
			return to;
		}
	};

	private Function<RotateGroupShopDTO, RotateGroupShopEntity> toRotateShopEntity = new Function<RotateGroupShopDTO, RotateGroupShopEntity>() {
		@Override
		public RotateGroupShopEntity apply(RotateGroupShopDTO from) {
			RotateGroupShopEntity to = null;
			if(from != null){
				to = new RotateGroupShopEntity();
				to.setId(from.getId());
				to.setRotateGroupID(from.getRotateGroupID());
				to.setShopID(from.getShopID());
				to.setShopGroupID(from.getShopGroupID());
				to.setStatus(from.getStatus());
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

		List<RotateGroupShopEntity> rotateGroupShops = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupIDList(rotateGroupIDs);
		for (RotateGroupShopEntity rotateGroupShop : rotateGroupShops) {
			if (!result.containsKey(rotateGroupShop.getRotateGroupID())) {
				result.put(rotateGroupShop.getRotateGroupID(), new ArrayList<RotateGroupShopDTO>());
			}
			result.get(rotateGroupShop.getRotateGroupID()).add(toRotateShopDTO.apply(rotateGroupShop));
		}
		return result;
	}

	@Override
	public List<RotateGroupShopDTO> getRotateGroupShopByShopGroupIDAndBizID(int shopGroupID, int bizID) {
		return Lists.transform(rotateGroupShopDAO.queryRotateGroupShopByShopGroupIDAndBizID(shopGroupID, bizID), toRotateShopDTO);
	}

	@Override
	public List<RotateGroupShopDTO> getRotateGroupShopByShopGroupIDAndBizIDAndCityID(int shopGroupID, int bizID, int cityID, int pageSize, int offset) {
		return Lists.transform(rotateGroupShopDAO.queryRotateGroupShopByShopGroupIDAndBizIDAndCityID(shopGroupID, bizID, cityID, pageSize, offset), toRotateShopDTO);
	}

	@Override
	public List<RotateGroupShopDTO> getAllRotateGroupShopByShopGroupIDAndBizIDAndCityID(int shopGroupID, int bizID, int cityID) {
		return Lists.transform(rotateGroupShopDAO.queryAllRotateGroupShopByShopGroupIDAndBizIDAndCityID(shopGroupID, bizID, cityID), toRotateShopDTO);
	}

	@Override
	public int getTotalNumByShopGroupIDAndBizIDAndCityID(int shopGroupID, int bizID, int cityID) {
		return rotateGroupShopDAO.getTotalNumByShopGroupIDAndBizIDAndCityID(shopGroupID, bizID, cityID);
	}

	@Override
	public void updateRotateGroupShop(RotateGroupShopDTO rotateGroupShopDTO) {
		rotateGroupShopDAO.updateRotateGroupShop(toRotateShopEntity.apply(rotateGroupShopDTO));
	}

    @Override
    public List<RotateGroupShopDTO> getRotateGroupShopWithNoStatus(int rotateGroupID){
        return Lists.transform(rotateGroupShopDAO.queryRotateGroupShopByRotateGroupIDWithNoStatus(rotateGroupID),toRotateShopDTO);
    }
}
