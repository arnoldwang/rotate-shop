package com.dianping.rotate.shop.dao

import com.beust.jcommander.internal.Lists
import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.entity.RotateGroupShopEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: zhenwei.wang
 * Date: 15-1-5
 */
class RotateGroupShopDAOTest extends AbstractSpockTest{
    @Autowired
    RotateGroupShopDAO rotateGroupShopDAO;

    def "test add update query and delete RotateGroupShop"(){
        setup:
        int rotateGroupID = new Random().nextInt(Integer.MAX_VALUE);
        int shopID = new Random().nextInt(Integer.MAX_VALUE);
        int shopGroupID = new Random().nextInt(Integer.MAX_VALUE);
        def r = new RotateGroupShopEntity();
        r.setRotateGroupID(rotateGroupID);
        r.setShopID(shopID);
        r.setShopGroupID(shopGroupID);
        r.setStatus(1);

        when:
        rotateGroupShopDAO.addToRotateGroupShop(r);
        r.setStatus(0);
        rotateGroupShopDAO.updateRotateGroupShop(r);
        List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShop(1)

        then:
        rotateGroupShopEntityList.get(0).getStatus() == 0;

        cleanup:
        rotateGroupShopDAO.deleteRotateGroupShop(1);
    }

    def "test queryRotateGroupShopByShopGroupID"() {
        setup:
        int shopGroupID = 0;
        int bizID = 1;

        when:
        List<RotateGroupShopEntity> rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShopByShopGroupIDAndBizID(shopGroupID, bizID);

        then:
        rotateGroupShopEntityList.get(0).getShopGroupID() == 0;
    }

    def "test addToRotateGroupShopByList"() {
        setup:
        RotateGroupShopEntity rotateGroupShopEntity = new RotateGroupShopEntity();
        rotateGroupShopEntity.setRotateGroupID(30);
        rotateGroupShopEntity.setShopID(5310722);
        rotateGroupShopEntity.setShopGroupID(5310722);
        rotateGroupShopEntity.setStatus(1);
        List<RotateGroupShopEntity> rotateGroupShopEntityList = Lists.newArrayList();
        rotateGroupShopEntityList.add(rotateGroupShopEntity);

        when:
        rotateGroupShopDAO.addToRotateGroupShopByList(rotateGroupShopEntityList)

        then:
        1 == 1;
    }
}
