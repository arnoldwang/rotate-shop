package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.ApolloShopBusinessStatusEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ApolloShopBusinessStatusDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopBusinessStatusEntity> queryApolloShopBusinessStatusByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopBusinessStatusEntity> queryApolloShopBusinessStatusByShopIDList(@DAOParam("shopIDList") List<Integer> shopIDList);

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopBusinessStatusEntity> queryApolloShopBusinessStatusByShopIDAndBusinessType(@DAOParam("shopID") int shopID, @DAOParam("businessType") int businessType);

    @DAOAction(action = DAOActionType.INSERT)
    int addApolloShopBusinessStatus(@DAOParam("apolloShopBusinessStatus") ApolloShopBusinessStatusEntity apolloShopBusiness);

    @DAOAction(action = DAOActionType.INSERT)
    void addApolloShopBusinessStatusBatch(@DAOParam("apolloShopBusinessStatusList") List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusList);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteApolloShopBusinessStatusByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteApolloShopBusinessStatusAll();

    @DAOAction(action = DAOActionType.DELETE)
    void clearApolloShopBusinessStatus();

    @DAOAction(action = DAOActionType.UPDATE)
    int updateApolloShopBusinessStatus(@DAOParam("apolloShopBusinessStatus") ApolloShopBusinessStatusEntity apolloShopBusiness);

}
