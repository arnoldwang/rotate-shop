package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.CategoryEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface CategoryDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<CategoryEntity> queryCategoryByCategoryID(@DAOParam("categoryID") int categoryID);

    @DAOAction(action = DAOActionType.QUERY)
    List<CategoryEntity> queryCategoryByCategoryIDAndCityID(@DAOParam("categoryID") int categoryID, @DAOParam("cityID") int cityID);

    @DAOAction(action = DAOActionType.INSERT)
    int addCategory(@DAOParam("category") CategoryEntity category);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteCategoryByCategoryID(@DAOParam("categoryID") int categoryID);

    @DAOAction(action = DAOActionType.UPDATE)
    int updateCategory(@DAOParam("category") CategoryEntity category);

}
