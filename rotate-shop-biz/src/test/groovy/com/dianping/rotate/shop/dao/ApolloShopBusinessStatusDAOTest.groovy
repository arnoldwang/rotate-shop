package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.entity.ApolloShopBusinessStatusEntity
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity
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

        cleanup:
        apolloShopBusinessDAO.deleteApolloShopBusinessStatusByShopID(shopID)
    }
}
