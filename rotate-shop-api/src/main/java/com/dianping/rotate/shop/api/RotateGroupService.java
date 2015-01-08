package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.RotateGroupDTO;
import com.dianping.rotate.shop.dto.RotateGroupExtendDTO;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public interface RotateGroupService {

	public RotateGroupDTO getRotateGroup(int rotateGroupID);

	public RotateGroupExtendDTO getRotateGroupExtend(int rotateGroupID);
}
