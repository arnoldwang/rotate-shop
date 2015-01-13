package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.dao.ApolloShopDAO
import com.dianping.rotate.shop.entity.ApolloShopEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by yangjie on 1/13/15.
 */
class ShopServiceTest extends AbstractSpockTest {
    @Autowired
    ApolloShopDAO apolloShopDAO;

    @Autowired
    ShopService shopService;

    def createShopEntity(int shopID) {
        def s = new ApolloShopEntity()
        s.setShopID(shopID)
        s.setShopGroupID(1)
        s.setCityID(1)
        s.setDistrict(1)
        s.setShopType(1)
        s.setStatus(1)
        return s;
    }


    def "merge shops"() {
        setup:
        int targetShopId = 99; // 合并后的shopid，目前的实现里面没有用到
        ApolloShopEntity entity = createShopEntity(1);
        apolloShopDAO.addApolloShop(entity)

        when:
        shopService.mergeShops(entity.getShopID(), targetShopId)

        then:
        apolloShopDAO.queryApolloShopByShopID(entity.getShopID()).size()  == 0;
    }

    def "restore merged shops"() {
        setup:
        int targetShopId = 99; // 合并后的shopid，目前的实现里面没有用到
        ApolloShopEntity entity = createShopEntity(1);
        apolloShopDAO.addApolloShop(entity)
        shopService.mergeShops(entity.getShopID(), targetShopId)

        when:
        shopService.restoreMergedShops(targetShopId, entity.getShopID())

        then:
        apolloShopDAO.queryApolloShopByShopID(entity.getShopID()).size()  != 0;

    }
}
