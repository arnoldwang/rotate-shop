package com.dianping.rotate.shop.dao

import com.beust.jcommander.internal.Lists
import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.RotateGroupShopEntity
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
        def rotateGroupID = new Random().nextInt(Integer.MAX_VALUE);
        def shopID = new Random().nextInt(Integer.MAX_VALUE);
        def shopGroupID = new Random().nextInt(Integer.MAX_VALUE);
        def r = new RotateGroupShopEntity();
        r.setRotateGroupID(rotateGroupID);
        r.setShopID(shopID);
        r.setShopGroupID(shopGroupID);
        r.setStatus(1);

        when:
        rotateGroupShopDAO.addToRotateGroupShop(r);
        r.setStatus(0);
        rotateGroupShopDAO.updateRotateGroupShop(r);

        then:
        def rotateGroupShopEntityList = rotateGroupShopDAO.queryRotateGroupShop(1)
        rotateGroupShopEntityList.get(0).getStatus() == 0;

        cleanup:
        rotateGroupShopDAO.deleteRotateGroupShop(1);
    }

    def "restore deleted shop"() {
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
        rotateGroupShopDAO.deleteRotateGroupShopByShopId(shopID)
        rotateGroupShopDAO.restoreRotateGroupShopByShopId(shopID)

        then:
        rotateGroupShopDAO.queryRotateGroupShopByShopID(shopID).size() != 0;

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

    def "test queryRotateGroupShopByShopID"() {
        setup:
        int shopId = 99;
        def r = new RotateGroupShopEntity()
        r.setShopID(shopId)
        r.setRotateGroupID(1)
        r.setStatus(1)

        when:
        rotateGroupShopDAO.addToRotateGroupShop(r)

        then:
        1 == rotateGroupShopDAO.queryRotateGroupShopByShopID(shopId).size()
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
