package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.entity.BuBizEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: zhenwei.wang
 * Date: 15-1-5
 */
class BuBizDAOTest extends AbstractSpockTest{
    @Autowired
    BuBizDAO buBizDAO;

    def "test add query update and delete BuBiz"(){
        setup:
        int bizID = 100;
        int buID = 300;
        def r = new BuBizEntity();
        r.setBizID(bizID);
        r.setBuID(buID);
        r.setStatus(1);

        when:
        buBizDAO.addToBuBiz(r);
        r.setStatus(5);
        r.setId(2)
        buBizDAO.updateBuBiz(r);
        List<BuBizEntity> buBizEntityList = buBizDAO.queryBuBiz(3)

        then:
        buBizEntityList.get(0).getStatus() == 0;

        cleanup:
        buBizDAO.deleteBuBiz(3);
    }
}
