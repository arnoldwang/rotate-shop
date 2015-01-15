package com.dianping.rotate.shop.dao;

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.RotateGroupEntity;
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
        int bizID = new Random().nextInt(Integer.MAX_VALUE);
        def r = new RotateGroupEntity();
        r.setBizID(bizID);
        r.setType(0);
        r.setStatus(0);

        when:
        int id = rotateGroupDAO.addToRotateGroup(r);
        r.setStatus(1);
        rotateGroupDAO.updateRotateGroup(r);
        List<RotateGroupEntity> rotateGroupEntityList = rotateGroupDAO.getRotateGroup(id)

        then:
        rotateGroupEntityList.get(0).getStatus() == 1;

        cleanup:
        rotateGroupDAO.deleteRotateGroup(id);
    }


}