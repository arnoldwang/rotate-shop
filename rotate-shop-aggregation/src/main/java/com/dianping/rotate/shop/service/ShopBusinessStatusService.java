package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.json.ApolloShopBusinessStatusEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/27.
 */
public interface ShopBusinessStatusService {

    public void deleteApolloShopBusinessStatusAll();

    public void addApolloShopBusinessStatus(List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList);

    public void clearApolloShopBusinessStatus();
}
