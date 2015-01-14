package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.ApolloShopService
import com.dianping.rotate.shop.api.RotateGroupService
import com.dianping.rotate.shop.dto.ApolloShopDTO
import com.dianping.rotate.shop.dto.RotateGroupDTO
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/12.
 */
class RotateGroupServiceTest extends AbstractSpockTest {

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
        null == rotateGroupDTO.getId();
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
        null == rotateGroupDTO.getId();
    }

    def "test getRotateGroupWithCustomerStatus when normal"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCustomerStatus(bizID, shopID);

        then:
        rotateGroupID == rotateGroupDTO.getId();
        1 == rotateGroupDTO.getCustomerStatus();
    }

    def "test getRotateGroupWithCustomerStatus when shopID is not exist"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCustomerStatus(bizID, -1);

        then:
        null == rotateGroupDTO.getId();
    }

    def "test getRotateGroupWithCustomerStatus when bizID is not exist"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCustomerStatus(-1, shopID);

        then:
        null == rotateGroupDTO.getId();
    }

    def "test getRotateGroupWithCustomerStatus when bizID and shopID is not exist"() {
        when:
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroupWithCustomerStatus(-1, -1);

        then:
        null == rotateGroupDTO.getId();
    }

}
