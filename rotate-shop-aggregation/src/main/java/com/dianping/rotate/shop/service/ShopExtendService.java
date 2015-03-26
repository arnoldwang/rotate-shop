package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.json.ApolloShopExtendEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/28.
 */
public interface ShopExtendService {

    public void updateApolloShopExtendRating(List<ApolloShopExtendEntity> apolloShopExtendEntityList);

    public List<ApolloShopExtendEntity> queryApolloShopExtendByShopIDListAndBizID(List<Integer> shopIDList, int bizID);

    public void addApolloShopExtend(List<ApolloShopExtendEntity> apolloShopExtendEntityList);

}
