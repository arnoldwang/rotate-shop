package com.dianping.rotate.shop.dao

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
        s.setDistrict('上海')
        s.setShopType(1)
        s.setStatus(1)
        apolloShopDAO.addApolloShop(s)

        then:
        1 == apolloShopDAO.queryApolloShopByShopID(shopID).get(0).getShopID()
        s.setDistrict('北京')
        apolloShopDAO.updateApolloShop(s)
        '北京'.equals(apolloShopDAO.queryApolloShopByShopID(shopID).get(0).getDistrict())

        cleanup:
        apolloShopDAO.deleteApolloShopByShopID(shopID)
    }
}
