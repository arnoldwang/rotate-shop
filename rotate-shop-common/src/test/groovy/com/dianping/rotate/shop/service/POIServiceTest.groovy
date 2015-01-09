package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.dao.RotateGroupShopDAO
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: zhenwei.wang
 * Date: 15-1-8
 */
class POIServiceTest extends AbstractSpockTest{
    @Autowired
    POIService poiService;

    @Autowired
    RotateGroupShopDAO rotateGroupShopDAO;

    def "test addPoiBySys"(){
        setup:
        String msg = "{'shopId': 5310725, 'messageType': 4}";

        when:
        poiService.addPoiBySys(msg);

        then:
        4 == 4
    }
}
