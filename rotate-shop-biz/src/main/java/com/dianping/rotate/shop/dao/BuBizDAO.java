package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.BuBizEntity;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-5
 */
public interface BuBizDAO extends GenericDao {

	@DAOAction(action = DAOActionType.INSERT)
	public void addToBuBiz(@DAOParam("buBiz")BuBizEntity buBiz);

	@DAOAction(action = DAOActionType.DELETE)
	public void deleteBuBiz(@DAOParam("id")int id);

	@DAOAction(action = DAOActionType.UPDATE)
	public void updateBuBiz(@DAOParam("buBiz")BuBizEntity buBiz);

	@DAOAction(action = DAOActionType.QUERY)
	public List<BuBizEntity> queryBuBiz(@DAOParam("id")int id);
}
