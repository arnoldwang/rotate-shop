package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.RotateGroupDTO;
import com.dianping.rotate.shop.dto.RotateGroupExtendDTO;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public interface RotateGroupService {

	public RotateGroupDTO getRotateGroup(int rotateGroupID);

	public List<RotateGroupDTO> getRotateGroup(List<Integer> rotateGroupIDList);

	public RotateGroupExtendDTO getRotateGroupExtend(int rotateGroupID);
}
