package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.ApolloShopBusinessStatusEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/4.
 */
class ApolloShopBusinessStatusDAOTest extends AbstractSpockTest {
    @Autowired
    ApolloShopBusinessStatusDAO apolloShopBusinessDAO;

    def "add, query and delete one apolloShopBusinessStatus"() {
        setup:
        int shopID = 1

        when:
        def s = new ApolloShopBusinessStatusEntity()
        s.setShopID(shopID)
        s.setCooperationStatus(0)
        s.setBusinessType(0)
        s.setStatus(1)
        apolloShopBusinessDAO.addApolloShopBusinessStatus(s)

        then:
        1 == apolloShopBusinessDAO.queryApolloShopBusinessStatusByShopID(shopID).get(0).getShopID()
        1 == apolloShopBusinessDAO.queryApolloShopBusinessStatusByShopIDAndBusinessType(shopID, 0).get(0).getShopID()
        s.setBusinessType(1)
        apolloShopBusinessDAO.updateApolloShopBusinessStatus(s)
        1 == apolloShopBusinessDAO.queryApolloShopBusinessStatusByShopID(shopID).get(0).getBusinessType()
        1 == apolloShopBusinessDAO.queryApolloShopBusinessStatusByShopIDAndBusinessType(shopID, 1).get(0).getShopID()

        cleanup:
        apolloShopBusinessDAO.deleteApolloShopBusinessStatusByShopID(shopID)
    }
}
