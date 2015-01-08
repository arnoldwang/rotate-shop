package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.RegionService
import com.dianping.rotate.shop.dto.RegionDTO
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by yangjie on 1/8/15.
 */
class ApolloRegionServiceTest extends AbstractSpockTest {
    @Autowired
    RegionService regionService;

    def "apollo region service"() {
        setup:
        int regionId = 10;

        when:
        RegionDTO dto = regionService.getRegion(regionId);

        then:
        dto.getCityID() == regionId;
    }
}
