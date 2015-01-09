package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.RotateGroupShopEntity;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-4
 */
public interface RotateGroupShopDAO extends GenericDao {

	@DAOAction(action = DAOActionType.INSERT)
	public int addToRotateGroupShop(@DAOParam("rotateGroupShop")RotateGroupShopEntity rotateGroupShop);

	@DAOAction(action = DAOActionType.DELETE)
	public void deleteRotateGroupShop(@DAOParam("id")int id);

	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroupShop(@DAOParam("rotateGroup")RotateGroupShopEntity rotateGroupShop);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupShopEntity> queryRotateGroupShop(@DAOParam("id")int id);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupShopEntity> queryRotateGroupShopByRotateGroupID(@DAOParam("rotateGroupID")int rotateGroupID);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupShopEntity> queryRotateGroupShopByShopGroupIDAndBizID(@DAOParam("shopGroupID")int shopGroupId, @DAOParam("bizID")int bizID);

	@DAOAction(action = DAOActionType.INSERT)
	public void addToRotateGroupShopByList(@DAOParam("rotateGroupShopList")List<RotateGroupShopEntity> rotateGroupShopEntityList);

	@DAOAction(action = DAOActionType.QUERY)
	public int getShopNumInSameRotateGroup(@DAOParam("shopID")int shopId);
}
