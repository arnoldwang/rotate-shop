package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.RotateGroupDTO;
import com.dianping.rotate.shop.dto.RotateGroupExtendDTO;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public interface RotateGroupService {

	/**
	 * 获取轮转组DTO，不包含任何扩展信息
	 * @param rotateGroupID 轮转组ID
	 * @return
	 */
	public RotateGroupDTO getRotateGroup(int rotateGroupID);

	/**
	 * 获取轮转组DTO，不包含任何扩展信息
	 * @param bizID bizID
	 * @param shopID 门店ID
	 * @return
	 */
	public RotateGroupDTO getRotateGroup(int bizID, int shopID);

	public List<RotateGroupDTO> getRotateGroup(List<Integer> rotateGroupIDList);

	/**
	 * 获取轮转组DTO，包括基本信息和合作状态
	 * @param rotateGroupID 轮转组ID
	 * @return
	 */
	public RotateGroupDTO getRotateGroupWithCooperationStatus(int rotateGroupID);

	/**
	 * 获取轮转组DTO，包括基本信息和客户状态
	 * @param rotateGroupID 轮转组ID
	 * @return
	 */
	public RotateGroupDTO getRotateGroupWithCustomerStatus(int rotateGroupID);

}
