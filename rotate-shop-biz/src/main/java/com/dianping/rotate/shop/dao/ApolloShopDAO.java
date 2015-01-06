package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.ApolloShopEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ApolloShopDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopEntity> queryApolloShopByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.INSERT)
    int addApolloShop(@DAOParam("apolloShop") ApolloShopEntity apolloShop);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteApolloShopByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.UPDATE)
    int updateApolloShop(@DAOParam("apolloShop") ApolloShopEntity apolloShop);

}
