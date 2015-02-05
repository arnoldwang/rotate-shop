package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.BizEntity;
import com.dianping.rotate.shop.json.TaskEntity;


/**
 * User: luo.ming
 * Date: 15-2-4
 */
public interface TaskDAO extends GenericDao {

	@DAOAction(action = DAOActionType.LOAD)
	public TaskEntity queryTask(@DAOParam("tableName") String tableName);

}
