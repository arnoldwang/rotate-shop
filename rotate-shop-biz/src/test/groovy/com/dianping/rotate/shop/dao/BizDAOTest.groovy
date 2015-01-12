package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.entity.BizEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: zhenwei.wang
 * Date: 15-1-5
 */
class BizDAOTest extends AbstractSpockTest{
    @Autowired
    BizDAO bizDAO;

    def "test add query update and delete Biz"(){
        setup:
        int bizID = 1;
        String name = "团购1";
        def r = new BizEntity();
        r.setBizID(bizID);
        r.setName(name);
        r.setStatus(1);

        when:
        bizDAO.addToBiz(r);
        r.setStatus(0);
        bizDAO.updateBiz(r);
        List<BizEntity> bizEntityList = bizDAO.queryBiz(1)

        then:
        bizEntityList.get(0).getBizID() == 1;
        bizEntityList.get(0).getStatus() == 0;

        cleanup:
        bizDAO.deleteBiz(1);
    }
}
