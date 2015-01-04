package com.dianping.rotate.shop.dao;

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.entity.RotateGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * User: zhenwei.wang
 * Date: 15-1-4
 */
public class RotateGroupDAOTest extends AbstractSpockTest {
    @Autowired
    RotateGroupDAO rotateGroupDAO;

    def "test add query update and delete RotateGroup"(){
        setup:
        int rotateGroupID = 2;
        int bizID = new Random().nextInt(Integer.MAX_VALUE);
        def r = new RotateGroupEntity();
        r.setRotateGroupID(rotateGroupID);
        r.setBizID(bizID);
        r.setType(0);
        r.setStatus(1);

        when:
        rotateGroupDAO.addToRotateGroup(r);
        r.setStatus(0);
        rotateGroupDAO.updateRotateGroup(r);
        List<RotateGroupEntity> rotateGroupEntityList = rotateGroupDAO.queryRotateGroup(2)

        then:
        rotateGroupEntityList.get(0).getRotateGroupID() == 2;
        rotateGroupEntityList.get(0).getStatus() == 0;

        cleanup:
        rotateGroupDAO.deleteRotateGroup(2);
    }
}