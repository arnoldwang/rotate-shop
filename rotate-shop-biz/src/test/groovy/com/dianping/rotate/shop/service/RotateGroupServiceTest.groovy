package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.ApolloShopService
import com.dianping.rotate.shop.api.RotateGroupService
import com.dianping.rotate.shop.dto.ApolloShopDTO
import com.dianping.rotate.shop.dto.RotateGroupDTO
import org.springframework.beans.factory.annotation.Autowired
import com.beust.jcommander.internal.Lists
import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.RotateGroupService
import com.dianping.rotate.shop.exceptions.RequestServiceException
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: zhenwei.wang
 * Date: 15-1-14
 */
class RotateGroupServiceTest extends AbstractSpockTest{

    @Autowired
    RotateGroupService rotateGroupService;

    int rotateGroupID = 11;
    int shopID = 500004;
    int bizID = 6;

    def "test getRotateGroup when normal"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroup(rotateGroupID);

        then:
        rotateGroupID == rotateGroupDTO.getId();
    }

    def "test getRotateGroup when rotateGroupID is not exist"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroup(-1);

        then:
        null == rotateGroupDTO;
    }

    def "test getRotateGroupWithCooperationStatus when normal"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCooperationStatus(rotateGroupID);

        then:
        rotateGroupID == rotateGroupDTO.getId();
        2 == rotateGroupDTO.getCooperationStatus();
    }

    def "test getRotateGroupWithCooperationStatus when rotateGroupID is not exist"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCooperationStatus(-1);

        then:
        null == rotateGroupDTO;
    }

    def "test getRotateGroupWithCustomerStatus when normal"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCustomerStatus(rotateGroupID);

        then:
        rotateGroupID == rotateGroupDTO.getId();
        1 == rotateGroupDTO.getCustomerStatus();
    }

    def "test getRotateGroupWithCustomerStatus when rotateGroupDTO is not exist"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCustomerStatus(-1);

        then:
        null == rotateGroupDTO;
    }

    final shouldFail = new GroovyTestCase().&shouldFail

    def "test getRotateGroup when rotateGroupID is wrong"() {
        setup:
        def rotateGroupID = 111111

        when:
        rotateGroupID = 111112

        then:
        null == rotateGroupService.getRotateGroup(rotateGroupID)
    }

    def "test getRotateGroup when rotateGroupID is right"() {
        setup:
        def rotateGroupID = 11

        when:
        def r = rotateGroupService.getRotateGroup(rotateGroupID)

        then:
        11 == r.getId()
    }

    def "test getRotateGroupList when rotateGroupIDList is right"() {
        setup:
        def rotateGroupIDList = Lists.newArrayList(9, 7, 8)

        when:
        def entityList = rotateGroupService.getRotateGroup(rotateGroupIDList)

        then:
        3 == entityList.size()
    }
}
