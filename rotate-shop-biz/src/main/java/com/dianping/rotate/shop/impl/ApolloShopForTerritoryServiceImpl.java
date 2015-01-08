package com.dianping.rotate.shop.impl;

import com.dianping.core.type.PageModel;
import com.dianping.rotate.shop.api.ApolloShopForTerritoryService;
import com.dianping.rotate.shop.dto.ApolloShopForTerritoryQueryDTO;


import org.springframework.stereotype.Service;

/**
 * Created by dev_wzhang on 15-1-8.
 */
@Service("apolloShopForTerritoryService")
public class ApolloShopForTerritoryServiceImpl implements ApolloShopForTerritoryService {

    @Override
    public PageModel batchFetchApolloShop(ApolloShopForTerritoryQueryDTO queryDto) {
        return null;
    }
}
