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

    int rotateGroupID = 69;
    int shopID = 500004;
    int bizID = 6;

    def "test getRotateGroup when normal"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroup(96757808);

        then:
        96757808 == rotateGroupDTO.getId();
    }

    def "test getRotateGroup when rotateGroupID is not exist"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroup(-1);

        then:
        null == rotateGroupDTO;
    }

    def "test getRotateGroupWithCooperationStatus when normal"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCooperationStatus(19);

        then:
        19 == rotateGroupDTO.getId();
        null == rotateGroupDTO.getCooperationStatus();
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
        0 == rotateGroupDTO.getCustomerStatus();
    }

    def "test getRotateGroupWithCustomerStatus when rotateGroupDTO is not exist"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCustomerStatus(-1);

        then:
        null == rotateGroupDTO;
    }

    final shouldFail = new GroovyTestCase().&shouldFail

    def "test getRotateGroup when rotateGroupID is wrong"() {

        when:
        rotateGroupID = 1111121

        then:
        null == rotateGroupService.getRotateGroup(rotateGroupID)
    }

    def "test getRotateGroup when rotateGroupID is right"() {

        when:
        def r = rotateGroupService.getRotateGroup(rotateGroupID)

        then:
        rotateGroupID == r.getId()
    }

    def "test getRotateGroupList when rotateGroupIDList is right"() {
        setup:
        def rotateGroupIDList = Lists.newArrayList(69,70,71)

        when:
        def entityList = rotateGroupService.getRotateGroup(rotateGroupIDList)

        then:
        3 == entityList.size()
    }

    def "test updateType"() {
        setup:
        def id = 0
        def type = 1

        when:
        rotateGroupService.updateType(id, type)

        then:
        1 == rotateGroupService.getRotateGroup(id).getType()
    }

    def "test getRotateGroupList"() {
        setup:
        def shopGroupId = 10225
        def bizId = 101

        when:
        def rotateGroupList = rotateGroupService.getRotateGroupList(shopGroupId, bizId)

        then:
        3 == rotateGroupList.size()
    }
}
