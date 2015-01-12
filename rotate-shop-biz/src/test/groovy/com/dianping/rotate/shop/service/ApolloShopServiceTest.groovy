package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.ApolloShopService
import com.dianping.rotate.shop.dto.ApolloShopDTO
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/12.
 */
class ApolloShopServiceTest extends AbstractSpockTest {

    @Autowired
    ApolloShopService apolloShopService;

    def "test getApolloShop when normal"() {
        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(4295868, 6);

        then:
        4295868 == apolloShopDTO.getShopID();
        6 == apolloShopDTO.getBizID();
    }

    def "test getApolloShop when shopID is not exist"() {
        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(-1, 6);

        then:
        null == apolloShopDTO.getShopID();
        null == apolloShopDTO.getBizID();
    }

    def "test getApolloShop when bizID is not exist"() {
        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(4295868, -1);

        then:
        4295868 == apolloShopDTO.getShopID();
        null == apolloShopDTO.getBizID();
    }

    def "test getApolloShop when shopID and bizID is not exist"() {
        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(-1, -1);

        then:
        null == apolloShopDTO.getShopID();
        null == apolloShopDTO.getBizID();
    }

}
