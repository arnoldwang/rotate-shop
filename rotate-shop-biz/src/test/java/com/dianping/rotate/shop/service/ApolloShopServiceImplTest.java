package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.AbstractTest;
import com.dianping.rotate.shop.api.ApolloShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaza on 15/3/6.
 */
public class ApolloShopServiceImplTest extends AbstractTest {
    @Autowired
    ApolloShopService apolloShopService;

    @Test
    public void updateApolloShopTypeByShopIDAndBizIDTest(){
        List<Integer> shopIds = new ArrayList<Integer>();
        shopIds.add(500009);
        if(apolloShopService.updateApolloShopTypeByShopIDAndBizID(shopIds,101,0)){
            System.out.println("update type success");
        }else{
            System.out.println("update type fail");
        }
    }
}
