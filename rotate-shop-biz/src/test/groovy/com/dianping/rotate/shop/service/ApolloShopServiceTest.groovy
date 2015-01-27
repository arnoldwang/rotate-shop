package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.ApolloShopService
import com.dianping.rotate.shop.dto.ApolloShopDTO
import com.dianping.rotate.shop.exceptions.RequestServiceException
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/12.
 */
class ApolloShopServiceTest extends AbstractSpockTest {

    @Autowired
    ApolloShopService apolloShopService;

    int shopID = 500004;
    int shopID_1 = 500005
    int bizID = 6;

    def "test getApolloShop when normal"() {
        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(shopID, bizID);

        then:
        shopID == apolloShopDTO.getShopID();
        bizID == apolloShopDTO.getBizID();
    }

    def "test getApolloShop when shopID is not exist"() {
        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(-1, bizID);

        then:
        null == apolloShopDTO;
    }

    def "test getApolloShop when bizID is not exist"() {
        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(shopID, -1);

        then:
        shopID == apolloShopDTO.getShopID();
        null == apolloShopDTO.getBizID();
    }

    def "test getApolloShop when shopID and bizID is not exist"() {
        when:
        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(-1, -1);

        then:
        null == apolloShopDTO;
    }

    def "test getApolloShop batch when normal"() {
        setup:
        List<Integer> shopIDList = new ArrayList<Integer>();
        shopIDList.add(shopID);
        shopIDList.add(shopID_1);

        when:
        List<ApolloShopDTO> apolloShopDTOList = apolloShopService.getApolloShop(shopIDList, bizID);

        then:
        2 == apolloShopDTOList.size();
        shopID_1 == apolloShopDTOList.get(0).getShopID();
        bizID == apolloShopDTOList.get(0).getBizID();
        shopID == apolloShopDTOList.get(1).getShopID();
        bizID == apolloShopDTOList.get(1).getBizID();
    }

    def "test getApolloShop batch when shopIDList size over 1000"() {
        when:
        List<Integer> shopIDList = new ArrayList<Integer>();
        for(int i=0;i<10001;i++) {
            shopIDList.add(shopID);
        }

        then:
        GroovyAssert.shouldFail(RequestServiceException){
            apolloShopService.getApolloShop(shopIDList, bizID);
        }
    }

    def "test getApolloShop batch when shopIDList is null"() {
        setup:
        List<Integer> shopIDList = null;

        when:
        List<ApolloShopDTO> apolloShopDTOList = apolloShopService.getApolloShop(shopIDList, bizID);

        then:
        0 == apolloShopDTOList.size();
    }

    def "test getApolloShop batch when bizID is not exist"() {
        setup:
        List<Integer> shopIDList = new ArrayList<Integer>();
        shopIDList.add(shopID);
        shopIDList.add(shopID_1);

        when:
        List<ApolloShopDTO> apolloShopDTOList = apolloShopService.getApolloShop(shopIDList, -1);

        then:
        2 == apolloShopDTOList.size();
        shopID_1 == apolloShopDTOList.get(0).getShopID();
        null == apolloShopDTOList.get(0).getBizID();
        shopID == apolloShopDTOList.get(1).getShopID();
        null == apolloShopDTOList.get(1).getBizID();
    }

    def "test getShopByRotateGroupID with wrong data"(){
        setup:
        def rotateGroupID = 100

        when:
        def shops = apolloShopService.getShopByRotateGroupID(rotateGroupID)

        then:
        0 == shops.size()
    }

    def "test getShopByRotateGroupID with right data"(){
        setup:
        def rotateGroupID = 586377

        when:
        def shops = apolloShopService.getShopByRotateGroupID(rotateGroupID)

        then:
        3 == shops.size()
    }

}
