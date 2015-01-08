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

    // 和平公园
    private int hepinggongyuan = 927

    def "get region"() {
        when:
        RegionDTO dto = regionService.getRegion(hepinggongyuan);

        then:
        dto.getRegionID() == hepinggongyuan;
    }

    def "get ancestors"() {
        when:
        List<RegionDTO> dtos = regionService.getRegionAncestors(hepinggongyuan);

        then:
        dtos.size() != 0;
    }
}
