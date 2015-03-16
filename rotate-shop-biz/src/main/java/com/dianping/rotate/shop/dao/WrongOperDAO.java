package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.BizEntity;
import com.dianping.rotate.shop.json.WrongOperEntity;


/**
 * User: luoming
 * Date: 15-3-12
 */
public interface WrongOperDAO extends GenericDao {

	@DAOAction(action = DAOActionType.INSERT)
	public int addWrongOper(@DAOParam("wrongOper") WrongOperEntity wrongOper);
}
