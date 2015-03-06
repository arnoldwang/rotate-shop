package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/3/6.
 */
public class ShopBusinessTaskTest extends AbstractTest {
    @Autowired
    ApolloShopBusinessDataProcessorTask shopBusinessTask;

    @Test
    public void processTest(){
        shopBusinessTask.send();
        System.out.println("send message!");
    }
}
