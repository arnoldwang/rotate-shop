package com.dianping.rotate.shop.dao;

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.AdApolloShopRatingEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sharon on 15-3-5.
 */
public class AdApolloShopRatingDAOTest extends AbstractSpockTest {
    @Autowired
    AdApolloShopRatingDAO adApolloShopRatingDao;

    def "add query delete"(){
        setup:
        List<Integer> shopIdList = new ArrayList<Integer>()
        shopIdList.add(1)

        int bizId = 104

        AdApolloShopRatingEntity entity = new AdApolloShopRatingEntity()
        entity.setShopId(shopIdList.get(0))
        entity.setBizId(bizId)
        entity.setType(1)
        entity.setRating(101)
        entity.setStatus(1)
        entity.setCreatedTime(new Date())

        when:
        adApolloShopRatingDao.insert(entity)
        //AdApolloShopRatingEntity resultAdApolloShopRatingEntity = adApolloShopRatingDao.queryAdApolloShopRating(shopIdList,bizId).get(0)
        //System.out.println("===="+resultAdApolloShopRatingEntity)

        then:
        1 == adApolloShopRatingDao.queryAdApolloShopRating(shopIdList,bizId).get(0).getShopId()

        //resultAdApolloShopRatingEntity.setRating(102)
        //adApolloShopRatingDao.update(resultAdApolloShopRatingEntity)

        //1 == adApolloShopRatingDao.queryAdApolloShopRating(shopIdList,bizId).get(0).getShopId()

//        cleanup:
//        adApolloShopRatingDao.delete(resultAdApolloShopRatingEntity.getId())
    }
}
