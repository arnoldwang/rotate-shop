package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.ApolloShopExtendEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/4.
 */
class ApolloShopExtendDAOTest extends AbstractSpockTest {
    @Autowired
    ApolloShopExtendDAO apolloShopExtendDAO;

    def "add, query and delete one apolloShopExtend"() {
        setup:
        int shopID = 1

        when:
        def s = new ApolloShopExtendEntity()
        s.setShopID(shopID)
        s.setType(0)
        s.setBizID(1)
        s.setRating('K')
        s.setStatus(1)
        apolloShopExtendDAO.addApolloShopExtend(s)

        then:
        1 == apolloShopExtendDAO.queryApolloShopExtendByShopID(shopID).get(0).getShopID()
        s.setRating('A')
        apolloShopExtendDAO.updateApolloShopExtend(s)
        'A'.equals(apolloShopExtendDAO.queryApolloShopExtendByShopID(shopID).get(0).getRating())
        'A'.equals(apolloShopExtendDAO.queryApolloShopExtendByShopIDAndBizID(shopID, 1).get(0).getRating())

        cleanup:
        apolloShopExtendDAO.deleteApolloShopExtendByShopID(shopID)
    }

    def "restore deleted shop"() {
        setup:
        ApolloShopExtendEntity entity = new ApolloShopExtendEntity()
        entity.setShopID(99)
        entity.setType(0)
        entity.setBizID(1)
        entity.setRating('K')
        entity.setStatus(1)
        apolloShopExtendDAO.addApolloShopExtend(entity);
        apolloShopExtendDAO.deleteApolloShopExtendByShopID(entity.getShopID())

        when:
        apolloShopExtendDAO.restoreApolloShopExtendByShopID(entity.getShopID());


        then:
        apolloShopExtendDAO.queryApolloShopExtendByShopID(entity.getShopID()).size() != 0;

    }
}
