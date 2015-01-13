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

    private final int REGION_LANDMARK = 927
    private final int REGION_BUSINESS_DISTRICT = 824;
    private final int DISTRICT = 9;

    private final int INVALID_REGION_ID = -1;

    def "get region"() {
        when:
        RegionDTO dto = regionService.getRegion(REGION_LANDMARK);

        then:
        dto.getRegionID() == REGION_LANDMARK;
    }

    // 非法regionid返回一个空的region对象
    def "get region with invalid region id"() {
        when:
        RegionDTO dto = regionService.getRegion(INVALID_REGION_ID);

        then:
        dto.getRegionID().equals(null);
    }

    def "get ancestors for landmark"() {
        when:
        List<RegionDTO> dtos = regionService.getRegionAncestors(REGION_LANDMARK);

        then:
        dtos.size() == 3;
    }

    def "get ancestors for business district"() {
        when:
        List<RegionDTO> dtos = regionService.getRegionAncestors(REGION_BUSINESS_DISTRICT);

        then:
        dtos.size() == 2;
    }

    def "get ancestors for district"() {
        when:
        List<RegionDTO> dtos = regionService.getRegionAncestors(DISTRICT);

        then:
        dtos.size() == 1;
    }

    def "get ancestors for invalid region id"() {
        when:
        List<RegionDTO> dtos = regionService.getRegionAncestors(INVALID_REGION_ID);

        then:
        dtos.size() == 0;
    }
}
