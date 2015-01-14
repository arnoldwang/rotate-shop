package com.dianping.rotate.shop.dao

import com.dianping.core.type.PageModel
import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.entity.ApolloShopEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/4.
 */
class ApolloShopDAOTest extends AbstractSpockTest {
    @Autowired
    ApolloShopDAO apolloShopDAO;

    def "add, query and delete one apolloShop"() {
        setup:
        int shopID = 1

        when:
        def s = new ApolloShopEntity()
        s.setShopID(shopID)
        s.setShopGroupID(1)
        s.setCityID(1)
        s.setDistrict(1)
        s.setShopType(1)
        s.setStatus(1)
        apolloShopDAO.addApolloShop(s)

        then:
        1 == apolloShopDAO.queryApolloShopByShopID(shopID).get(0).getShopID()
        s.setDistrict(2)
        apolloShopDAO.updateApolloShop(s)
        2 == apolloShopDAO.queryApolloShopByShopID(shopID).get(0).getDistrict()

        cleanup:
        apolloShopDAO.deleteApolloShopByShopID(shopID)
    }

    def "batch Query Shop For Territory"(){
        setup:
        String ruleExpression=" cityID=8 AND ShopType = 50 and District=38"
        int bizId=1

        when:
        PageModel result = apolloShopDAO.queryApolloShopsForTerritory(ruleExpression,bizId,10,1,100,1)

        then:
        result.getRecordCount()>0

    }

    def createShopEntity(int shopID) {
        def s = new ApolloShopEntity()
        s.setShopID(shopID)
        s.setShopGroupID(1)
        s.setCityID(1)
        s.setDistrict(1)
        s.setShopType(1)
        return s;
    }

    def "restore deleted shop"() {
        setup:
        ApolloShopEntity entity = createShopEntity(1);
        apolloShopDAO.addApolloShop(entity)
        apolloShopDAO.deleteApolloShopByShopID(entity.getShopID())

        when:
        apolloShopDAO.restoreApolloShopByShopID(entity.getShopID());


        then:
        apolloShopDAO.queryApolloShopByShopID(entity.getShopID()).size() != 0;

    }

}
