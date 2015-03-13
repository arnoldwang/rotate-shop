package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.RotateGroupDTO;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public interface RotateGroupService {

	/**
	 * 获取轮转组DTO
	 * @param rotateGroupID 轮转组ID
	 * @return
	 */
	public RotateGroupDTO getRotateGroup(int rotateGroupID);

	/**
	 * 获取轮转组DTO
	 * @param bizID bizID
	 * @param shopID 门店ID
	 * @return
	 */
	public RotateGroupDTO getRotateGroup(int bizID, int shopID);

	/**
	 * 批量获取轮转组DTO，不包含任何扩展信息
	 * @param rotateGroupIDList 轮转组ID列表
	 * @return
	 */
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

	/**
	 * 合并轮转组，rotateGroupIDList里的rotateGroupIDs合并到rotateGroupID
	 * @param rotateGroupID
	 * @param rotateGroupIDList
	 * @return
	 */
	public boolean mergeRotateGroup(int rotateGroupID, List<Integer> rotateGroupIDList);

	/**
	 * 创建轮转组
	 * @param bizID
	 * @param apolloShopIDList
	 * @return
	 */
	public RotateGroupDTO createRotateGroup(int bizID, List<Integer> apolloShopIDList);

	/**
	 * 删除轮转组
	 * @param rotateGroupID
	 * @return
	 */
	public boolean deleteRotateGroupByRotateGroupID(int rotateGroupID);

	/**
	 * 创建一个新的轮转组
	 * @param bizID
	 * @return
	 */
	public RotateGroupDTO createRotateGroup(int bizID);

	/**
	 * 更新轮转组类型
	 * @param rotateGroupID
	 * @param type
	 */
	public void updateType(int rotateGroupID, int type);

	/**
	 * 获取轮转组DTO
	 * @param shopGroupID
	 * @param bizID
	 * @return
	 */
	public List<RotateGroupDTO> getRotateGroupList(int shopGroupID, int bizID);

    /**
    * 获取所有轮转组DTO，包括软删的RotateGroup
    * @param rotateGroupID 轮转组ID
    * @return
    */
    public List<RotateGroupDTO> getRotateGroupWithNoStatus(int rotateGroupID);

}
