package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.ShopRegionEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/4.
 */
class ShopRegionDAOTest extends AbstractSpockTest {
    @Autowired
    ShopRegionDAO shopRegionDAO;

    def "add, query and delete one region"() {
        setup:
        int shopID = 1
        int regionID = 2

        when:
        def s = new ShopRegionEntity()
        s.setShopID(shopID)
        s.setRegionID(regionID)
        s.setIsMain(1)
        s.setStatus(1)
        shopRegionDAO.addShopRegion(s)

        then:
        1 == shopRegionDAO.queryShopRegionByShopID(shopID).get(0).getShopID()
        2 == shopRegionDAO.queryShopRegionByRegionID(regionID).get(0).getRegionID()
        1 == shopRegionDAO.queryShopRegionByShopIDAndRegionID(1, 2).get(0).getIsMain()
        s.setIsMain(0)
        s.setId(shopRegionDAO.queryShopRegionByShopIDAndRegionID(1, 2).get(0).getId())
        shopRegionDAO.updateShopRegion(s)
        0 == shopRegionDAO.queryShopRegionByShopIDAndRegionID(1, 2).get(0).getIsMain()

        cleanup:
        shopRegionDAO.deleteShopRegionByRegionID(regionID)
        shopRegionDAO.deleteShopRegionByShopID(shopID)
        shopRegionDAO.deleteShopRegionByShopIDAndRegionID(shopID, regionID)
    }

    def "restore deleted shop"() {
        setup:
        int shopID = 1
        int regionID = 2

        def s = new ShopRegionEntity()
        s.setShopID(shopID)
        s.setRegionID(regionID)
        s.setIsMain(1)
        s.setStatus(1)
        shopRegionDAO.addShopRegion(s)
        shopRegionDAO.deleteShopRegionByShopID(shopID);

        when:
        shopRegionDAO.restoreShopRegionByShopID(shopID)


        then:
        shopRegionDAO.queryShopRegionByShopID(s.getShopID()).size() != 0;

    }
}
