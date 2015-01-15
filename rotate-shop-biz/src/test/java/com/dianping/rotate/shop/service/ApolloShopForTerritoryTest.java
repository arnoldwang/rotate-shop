package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.AbstractTest;
import com.dianping.rotate.shop.api.ApolloShopForTerritoryService;
import com.dianping.rotate.shop.dto.ApolloShopForTerritoryQueryDTO;
import com.dianping.rotate.shop.impl.ApolloShopForTerritoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dev_wzhang on 15-1-9.
 */
public class ApolloShopForTerritoryTest extends AbstractTest {

    @Autowired
    ApolloShopForTerritoryService apolloShopForTerritoryService;

    @Test
    public void testBatchFetchApolloShopID() {
        String where = "( cityID = 1 )";
        int startIndex =0;
        int endIndex=1000;
        List<Integer> idList = apolloShopForTerritoryService.batchFetchApolloShopID(where,startIndex,endIndex);
        Assert.assertTrue(idList.size()>0);
    }
}
