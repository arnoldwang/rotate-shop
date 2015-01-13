package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.dao.ApolloShopDAO
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: zhenwei.wang
 * Date: 15-1-8
 */
class POIServiceTest extends AbstractSpockTest{
    @Autowired
    POIService poiService;

    @Autowired
    ApolloShopDAO apolloShopDAO;

    def "test addPoiBySys with single shop"(){
        setup:
        String msg = "{'shopId': 500000, 'messageType': 4}";

        when:
        poiService.addPoiBySys(msg);

        then:
        def shop = apolloShopDAO.queryApolloShopByShopID(500000).get(0);
        500000 == shop.getShopID()
    }

    def "test addPoiBySys with mul shop"(){
        setup:
        String msg = "{'shopId': 500012, 'messageType': 4}";

        when:
        poiService.addPoiBySys(msg);

        then:
        def shop = apolloShopDAO.queryApolloShopByShopID(500012).get(0);
        500012 == shop.getShopID()
    }

    def "test addPoiBySys with wrong data"(){
        setup:
        String msg = "{'shopId': 11111, 'messageType': 4}";

        when:
        poiService.addPoiBySys(msg);

        then:
        1 == 1;
    }
}
