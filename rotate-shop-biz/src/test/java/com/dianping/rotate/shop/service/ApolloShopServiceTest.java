package com.dianping.rotate.shop.service;

import static org.mockito.Mockito.*;

import com.dianping.rotate.shop.AbstractTest;
import com.dianping.rotate.shop.api.ApolloShopService;
import com.dianping.rotate.shop.impl.ApolloShopServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by luoming on 15/1/9.
 */
public class ApolloShopServiceTest extends AbstractTest {

    @Test
    public void getApolloShop() {
        //创建mock对象，参数可以是类，也可以是接口
        ApolloShopService apolloShopService = mock(ApolloShopServiceImpl.class);

        //设置方法的预期返回值
        when(apolloShopService.getApolloShop(1, 1).getShopID()).thenReturn(1);

        int shopID = apolloShopService.getApolloShop(1, 1).getShopID();

        //验证方法调用(是否调用了get(0))
        verify(apolloShopService).getApolloShop(1, 1);

        //junit测试
        Assert.assertEquals(1, shopID);
    }

}
