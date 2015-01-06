package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.ApolloShopService
import com.dianping.rotate.shop.dto.ApolloShopDTO
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/6.
 */
class ApolloShopServiceTest extends AbstractSpockTest {

    @Autowired
    ApolloShopService apolloShopService;

    def "pigeon service ApolloShopService"() {
        setup:
        int shopID = 5308516
        int bizID = 1

        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(shopID, bizID)

        then:
        1 == apolloShopDTO.getBizID()
        5308516 == apolloShopDTO.getShopID()

    }

}
