package com.dianping.rotate.shop.dao

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
}
