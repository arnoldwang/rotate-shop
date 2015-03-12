package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.AdApolloShopRatingEntity;

import java.util.List;

/**
 * Created by sharon on 15-3-5.
 */
public interface AdApolloShopRatingDAO extends GenericDao {
    @DAOAction(action = DAOActionType.QUERY)
    public List<AdApolloShopRatingEntity> queryAdApolloShopRating(@DAOParam("shopId")Integer shopId,@DAOParam("bizId")int bizId);

    @DAOAction(action = DAOActionType.INSERT)
    public int insert(@DAOParam("adApolloShopRating")AdApolloShopRatingEntity adApolloShopRating);

    @DAOAction(action = DAOActionType.DELETE)
    public int delete(@DAOParam("id") int id);

    @DAOAction(action = DAOActionType.UPDATE)
    public int update(@DAOParam("adApolloShopRating")AdApolloShopRatingEntity adApolloShopRating);
}
