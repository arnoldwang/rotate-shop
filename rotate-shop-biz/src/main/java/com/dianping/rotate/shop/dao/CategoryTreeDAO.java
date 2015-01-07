package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.CategoryEntity;
import com.dianping.rotate.shop.entity.CategoryTreeEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface CategoryTreeDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<CategoryTreeEntity> queryMainCategoryTreeByCategoryIDAndCityID(@DAOParam("categoryID") int categoryID, @DAOParam("cityID") int cityID);

}
