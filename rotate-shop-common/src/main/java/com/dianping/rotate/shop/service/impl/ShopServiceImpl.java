package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.api.ApolloShopService;
import com.dianping.rotate.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangjie on 1/13/15.
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ApolloShopService apolloShopService;

    @Override
    public void mergeShops(int shopId, int toShopId) {
        apolloShopService.deleteApolloShopByShopID(shopId);
    }

    @Override
    public void restoreMergedShops(int shopId, int restoreShopId) {
        apolloShopService.restoreApolloShopByShopID(restoreShopId);
    }
}
