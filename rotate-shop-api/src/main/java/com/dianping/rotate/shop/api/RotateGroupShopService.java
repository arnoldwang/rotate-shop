package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.RotateGroupShopDTO;

import java.util.List;
import java.util.Map;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public interface RotateGroupShopService {

	public List<RotateGroupShopDTO> getRotateGroupShop(int rotateGroupID);

	public Map<Integer, List<RotateGroupShopDTO>> getRotateGroupShop(List<Integer> rotateGroupIDs);

	public List<RotateGroupShopDTO> getRotateGroupShopByShopGroupIDAndBizID(int shopGroupID, int bizID);
}
