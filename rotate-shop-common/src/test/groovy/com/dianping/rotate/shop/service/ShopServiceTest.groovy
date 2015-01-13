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

    def "test addPoiBySys with single shop"(){
        setup:
        String msg = "{'shopId': 500000, 'messageType': 4}";

        when:
        shopService.addPoiBySys(msg);

        then:
        def shop = apolloShopDAO.queryApolloShopByShopID(500000).get(0);
        500000 == shop.getShopID()
    }

    def "test addPoiBySys with mul shop"(){
        setup:
        String msg = "{'shopId': 500012, 'messageType': 4}";

        when:
        shopService.addPoiBySys(msg);

        then:
        def shop = apolloShopDAO.queryApolloShopByShopID(500012).get(0);
        500012 == shop.getShopID()
    }

    def "test addPoiBySys with wrong data"(){
        setup:
        String msg = "{'shopId': 11111, 'messageType': 4}";

        when:
        shopService.addPoiBySys(msg);

        then:
        1 == 1;
    }

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
