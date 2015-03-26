package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.RotateGroupEntity;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-4
 */
public interface RotateGroupDAO extends GenericDao {

	/**
	 * 插入一个新轮转组
	 *
	 * @param rotateGroup
	 * @return
	 */
	@DAOAction(action = DAOActionType.INSERT)
	public int addToRotateGroup(@DAOParam("rotateGroup") RotateGroupEntity rotateGroup);

	/**
	 * 删除一个轮转组，软删除
	 *
	 * @param rotateGroupID
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void deleteRotateGroup(@DAOParam("id") int rotateGroupID);

	/**
	 * 批量删除轮转组，软删除
	 *
	 * @param rotateGroupIDList
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void deleteRotateGroupBatch(@DAOParam("rotateGroupIDList") List<Integer> rotateGroupIDList);

	/**
	 * 重新开启一个轮转组
	 *
	 * @param rotateGroupID
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void restoreRotateGroup(@DAOParam("id") int rotateGroupID);

	/**
	 * 更新轮转组信息
	 *
	 * @param rotateGroup
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroup(@DAOParam("rotateGroup") RotateGroupEntity rotateGroup);

	/**
	 * 按轮转组ID查询轮转组
	 *
	 * @param rotateGroupID
	 * @return 只返回未删除的轮转组实体
	 */
	@DAOAction(action = DAOActionType.LOAD)
	public RotateGroupEntity getRotateGroup(@DAOParam("id") int rotateGroupID);

	/**
	 * 按轮转组ID列表批量查询轮转组
	 *
	 * @param rotateGroupIDList
	 * @return 只返回未删除的轮转组实体列表
	 */
	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupEntity> getRotateGroupList(@DAOParam("rotateGroupIDList") List<Integer> rotateGroupIDList);

	/**
	 * 按轮转组ID查询轮转组
	 *
	 * @param rotateGroupID
	 * @return 返回所有轮转组实体
	 */
	@DAOAction(action = DAOActionType.LOAD)
	public RotateGroupEntity getRotateGroupIgnoreStatus(@DAOParam("id") int rotateGroupID);

	/**
	 * 按shopID和bizID查询轮转组
	 *
	 * @param bizID
	 * @param shopID
	 * @return 轮转组列表
	 */
	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupEntity> queryRotateGroupByBizIDAndShopID(@DAOParam("bizID") int bizID, @DAOParam("shopID") int shopID);

	/**
	 * 分页获取RotateGroupID
	 *
	 * @param pageSize 每页大小
	 * @param offset   偏移量
	 * @return ID列表
	 */
	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupEntity> queryRotateGroupIDList(@DAOParam("pageSize") int pageSize, @DAOParam("offset") int offset);

	/**
	 * 查询RotateGroup的总数量，包括关闭的
	 *
	 * @return
	 */
	@DAOAction(action = DAOActionType.LOAD)
	public int getRotateGroupNum();

	/**
	 * 按门店查询轮转组，包括关闭的轮转组
	 *
	 * @param shopId
	 * @return
	 */
	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupEntity> queryRotateGroupByShopID(@DAOParam("shopID") int shopId);

	/**
	 * 跟新轮转组type
	 *
	 * @param rotateGroupID
	 * @param type
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroupTypeByID(@DAOParam("id") int rotateGroupID, @DAOParam("type") int type);

	/**
	 * 根据ShopGroupID和BizId获得轮转组
	 *
	 * @param shopGroupID
	 * @param bizID
	 * @return
	 */
	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupEntity> queryRotateGroupByShopGroupIDAndBizID(@DAOParam("shopGroupID") int shopGroupID,
																		 @DAOParam("bizID") int bizID);

	/**
	 * 按RotateGroupID查询轮转组，包括删除的轮转组
	 *
	 * @param rotateGroupID
	 * @return
	 */
	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupEntity> getRotateGroupWithNoStatus(@DAOParam("id") int rotateGroupID);

	/**
	 * 更新轮转组type，此方法供合并拆分轮转组使用，不发出swallow消息
	 * @param rotateGroupID
	 * @param type
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroupTypeUsedBySplitAndMerge(@DAOParam("id") int rotateGroupID, @DAOParam("type") int type);

	/**
	 * 软删除轮转组，此方法供合并拆分轮转组使用，不发出swallow消息
	 * @param rotateGroupID
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void deleteRotateGroupUsedBySplitAndMerge(@DAOParam("id")int rotateGroupID);
}
