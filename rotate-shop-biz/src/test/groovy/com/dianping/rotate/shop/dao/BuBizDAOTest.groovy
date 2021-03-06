package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.BuBizEntity
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
        def id = buBizDAO.addToBuBiz(r);
        r.setStatus(5);
        r.setId(id)
        buBizDAO.updateBuBiz(r);
        List<BuBizEntity> buBizEntityList = buBizDAO.queryBuBiz(id)

        then:
        buBizEntityList.get(0).getStatus() == 5;

        cleanup:
        buBizDAO.deleteBuBiz(id);
    }
}
