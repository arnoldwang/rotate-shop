package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;

/**
 * Created by luoming on 15/1/4.
 */
public interface ApolloShopExtendDAO extends GenericDao {

    @DAOAction(action = DAOActionType.LOAD)
    ApolloShopExtendEntity queryApolloShopExtendByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.LOAD)
    ApolloShopExtendEntity queryApolloShopExtendByShopIDAndBizID(@DAOParam("shopID") int shopID, @DAOParam("bizID") int bizID);

    @DAOAction(action = DAOActionType.INSERT)
    int addApolloShopExtend(@DAOParam("apolloShop") ApolloShopExtendEntity apolloShopExtend);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteApolloShopExtendByShopID(@DAOParam("shopID") int shopID);

}
