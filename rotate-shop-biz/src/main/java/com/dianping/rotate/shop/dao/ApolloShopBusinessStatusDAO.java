package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.ApolloShopBusinessStatusEntity;

/**
 * Created by luoming on 15/1/4.
 */
public interface ApolloShopBusinessStatusDAO extends GenericDao {

    @DAOAction(action = DAOActionType.LOAD)
    ApolloShopBusinessStatusEntity queryApolloShopBusinessStatusByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.LOAD)
    ApolloShopBusinessStatusEntity queryApolloShopBusinessStatusByShopIDAndBusinessType(@DAOParam("shopID") int shopID, @DAOParam("bussinessType") int bussinessType);

    @DAOAction(action = DAOActionType.INSERT)
    int addApolloShopBusinessStatus(@DAOParam("apolloShop") ApolloShopBusinessStatusEntity apolloShop);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteApolloShopBusinessStatusByShopID(@DAOParam("shopID") int shopID);

}
