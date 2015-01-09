package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.AbstractTest;
import com.dianping.rotate.shop.api.ApolloShopForTerritoryService;
import com.dianping.rotate.shop.dto.ApolloShopForTerritoryQueryDTO;
import com.dianping.rotate.shop.impl.ApolloShopForTerritoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dev_wzhang on 15-1-9.
 */
public class ApolloShopForTerritoryTest extends AbstractTest {
    @Test
    public void batchFetchApolloShop() {
        //创建mock对象，参数可以是类，也可以是接口
        ApolloShopForTerritoryService apolloShopService = mock(ApolloShopForTerritoryServiceImpl.class);

        ApolloShopForTerritoryQueryDTO queryDTO = new ApolloShopForTerritoryQueryDTO();
        queryDTO.setBizId(1);
        queryDTO.setModKey(10);
        queryDTO.setModValue(1);
        queryDTO.setPageIndex(1);
        queryDTO.setPageSize(100);
        queryDTO.setTerritoryRule(" cityID=8 AND ShopType = 50 and District=38 ");

        //设置方法的预期返回值
        when(apolloShopService.batchFetchApolloShop(queryDTO).getRecordCount()).thenReturn(1);

        int recordCount = apolloShopService.batchFetchApolloShop(queryDTO).getRecordCount();

        //验证方法调用(是否调用了get(0))
        verify(apolloShopService).batchFetchApolloShop(queryDTO);

        //junit测试
        Assert.assertTrue(recordCount>0);
    }
}
