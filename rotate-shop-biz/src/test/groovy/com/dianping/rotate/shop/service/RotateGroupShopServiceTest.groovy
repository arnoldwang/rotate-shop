package com.dianping.rotate.shop.service

import com.beust.jcommander.internal.Lists
import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.RotateGroupShopService
import com.dianping.rotate.shop.dao.RotateGroupShopDAO
import com.dianping.rotate.shop.dto.RotateGroupShopDTO
import com.dianping.rotate.shop.json.RotateGroupShopEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: zhenwei.wang
 * Date: 15-2-6
 */
class RotateGroupShopServiceTest extends AbstractSpockTest {
    @Autowired
    RotateGroupShopService rotateGroupShopService;

    @Autowired
    RotateGroupShopDAO rotateGroupShopDAO;

    def "test getRotateGroupShop by single"() {
        setup:
        def rotateGroupId = 1000
        createRotateGroupShop(rotateGroupId)

        when:
        List<RotateGroupShopDTO> dto = rotateGroupShopService.getRotateGroupShop(rotateGroupId)

        then:
        1 == dto.size()
        1000 == dto.get(0).getRotateGroupID()

    }

    def  "test getRotateGroupShop by mult"(){
        setup:
        def List<Integer> ids = Lists.newArrayList(1000, 1001)
        for(int id: ids){
            createRotateGroupShop(id)
        }

        when:
        Map<Integer, List<RotateGroupShopDTO>> map = rotateGroupShopService.getRotateGroupShop(ids)

        then:
        2 == map.size()
        1001 == map.get(1001).get(0).getRotateGroupID()
    }

    def createRotateGroupShop(int rotateGroupId) {
        def RotateGroupShopEntity r = new RotateGroupShopEntity()
        r.setStatus(1)
        r.setRotateGroupID(rotateGroupId)
        r.setShopID(10001)
        r.setShopGroupID(2000)
        rotateGroupShopDAO.addToRotateGroupShop(r)
    }

    def "test getRotateGroupShopByShopGroupIDAndBizIDAndCityID"(){
        setup:
        def shopGroupID = 10003
        def bizID = 101
        def cityID = 1
        def pageSize = 10
        def pageIndex = 0

        when:
        List<RotateGroupShopDTO> rotateGroupShopDTOs = rotateGroupShopService.getRotateGroupShopByShopGroupIDAndBizIDAndCityID(shopGroupID,bizID,cityID,pageSize,pageIndex)

        then:
        1 == rotateGroupShopDTOs.size()
    }
}
