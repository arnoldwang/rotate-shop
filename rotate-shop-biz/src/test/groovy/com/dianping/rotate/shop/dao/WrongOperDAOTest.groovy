package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.constants.WrongOperEnum
import com.dianping.rotate.shop.json.RegionEntity
import com.dianping.rotate.shop.json.WrongOperEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/4.
 */
class WrongOperDAOTest extends AbstractSpockTest {
    @Autowired
    WrongOperDAO wrongOperDAO;

    def "add one WrongOper"() {
        setup:
        WrongOperEntity wrongOperEntity = new WrongOperEntity();
        wrongOperEntity.setSource("1");
        wrongOperEntity.setTarget("1,2,3");
        wrongOperEntity.setType(WrongOperEnum.SHOP_ROTATEGROUP_MERGE.getCode());

        when:
        int id = wrongOperDAO.addWrongOper(wrongOperEntity);

        then:
        1 == id

    }
}
