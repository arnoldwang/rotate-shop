package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.ShopCategoryEntity;
import com.dianping.rotate.shop.entity.ShopCategoryEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ShopCategoryDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopCategoryByCategoryID(@DAOParam("categoryID") int categoryID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopCategoryByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopCategoryByShopIDAndCategoryID(@DAOParam("shopID") int shopID, @DAOParam("categoryID") int categoryID);

    @DAOAction(action = DAOActionType.INSERT)
    int addShopCategory(@DAOParam("shopCategory") ShopCategoryEntity shopCategory);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopCategoryByCategoryID(@DAOParam("categoryID") int categoryID);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopCategoryByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopCategoryByShopIDAndCategoryID(@DAOParam("shopID") int shopID, @DAOParam("categoryID") int categoryID);

    @DAOAction(action = DAOActionType.UPDATE)
    int updateShopCategory(@DAOParam("shopCategory") ShopCategoryEntity shopCategory);

}
