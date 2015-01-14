package com.dianping.rotate.shop.service

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

    final shouldFail = new GroovyTestCase().&shouldFail

    def "test getRotateGroup when rotateGroupID is wrong"() {
        setup:
        def rotateGroupID = 111111

        when:
        rotateGroupID = 111112

        then:
        shouldFail(RequestServiceException){
            rotateGroupService.getRotateGroup(rotateGroupID)
        }
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
