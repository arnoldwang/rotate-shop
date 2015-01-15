package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.RotateGroupEntity;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-4
 */
public interface RotateGroupDAO extends GenericDao{

	/**
	 * 插入一个新轮转组
	 * @param rotateGroup
	 * @return
	 */
	@DAOAction(action = DAOActionType.INSERT)
	public int addToRotateGroup(@DAOParam("rotateGroup")RotateGroupEntity rotateGroup);

	/**
	 * 删除一个轮转组，软删除
	 * @param rotateGroupID
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void deleteRotateGroup(@DAOParam("id")int rotateGroupID);

	/**
	 * 重新开启一个轮转组
	 * @param rotateGroupID
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void restoreRotateGroup(@DAOParam("id")int rotateGroupID);

	/**
	 * 更新轮转组信息
	 * @param rotateGroup
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroup(@DAOParam("rotateGroup")RotateGroupEntity rotateGroup);

	/**
	 * 按轮转组ID查询轮转组
	 * @param rotateGroupID
	 * @return 轮转组实体
	 */
	@DAOAction(action = DAOActionType.LOAD)
	public RotateGroupEntity getRotateGroup(@DAOParam("id") int rotateGroupID);

	/**
	 * 按轮转组ID列表批量查询轮转组
	 * @param rotateGroupIDList
	 * @return 轮转组实体列表
	 */
	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupEntity> getRotateGroupList(@DAOParam("rotateGroupIDList") List<Integer> rotateGroupIDList);

	/**
	 *
	 * @param rotateGroupID
	 * @return
	 */
	@DAOAction(action = DAOActionType.LOAD)
	public RotateGroupEntity getRotateGroupIgnoreStatus(@DAOParam("id") int rotateGroupID);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupEntity> queryRotateGroupByBizIDAndShopID(@DAOParam("bizID")int bizID, @DAOParam("shopID")int shopID);
}
