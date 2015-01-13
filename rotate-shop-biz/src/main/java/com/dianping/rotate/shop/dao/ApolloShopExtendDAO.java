package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ApolloShopExtendDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopExtendEntity> queryApolloShopExtendByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopExtendEntity> queryApolloShopExtendByShopIDAndBizID(@DAOParam("shopID") int shopID, @DAOParam("bizID") int bizID);

    @DAOAction(action = DAOActionType.INSERT)
    int addApolloShopExtend(@DAOParam("apolloShopExtend") ApolloShopExtendEntity apolloShopExtend);

    @DAOAction(action = DAOActionType.UPDATE)
    void deleteApolloShopExtendByShopID(@DAOParam("shopID") int shopID);


    @DAOAction(action = DAOActionType.UPDATE)
    void restoreApolloShopExtendByShopID(@DAOParam("shopID") int shopID);


    @DAOAction(action = DAOActionType.UPDATE)
    int updateApolloShopExtend(@DAOParam("apolloShopExtend") ApolloShopExtendEntity apolloShopExtend);

	@DAOAction(action = DAOActionType.LOAD)
	int getApolloShopExtendNumByShopID(@DAOParam("shopID") int shopId);
}
