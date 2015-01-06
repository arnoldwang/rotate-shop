package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.entity.ShopCategoryEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/4.
 */
class ShopCategoryDAOTest extends AbstractSpockTest {
    @Autowired
    ShopCategoryDAO shopCategoryDAO;

    def "add, query and delete one category"() {
        setup:
        int shopID = 1
        int categoryID = 2

        when:
        def s = new ShopCategoryEntity()
        s.setShopID(shopID)
        s.setCategoryID(categoryID)
        s.setIsMain(1)
        s.setStatus(1)
        shopCategoryDAO.addShopCategory(s)

        then:
        1 == shopCategoryDAO.queryShopCategoryByShopID(shopID).get(0).getShopID()
        2 == shopCategoryDAO.queryShopCategoryByCategoryID(categoryID).get(0).getCategoryID()
        1 == shopCategoryDAO.queryShopCategoryByShopIDAndCategoryID(1, 2).get(0).getIsMain()
        s.setIsMain(0)
        s.setId(shopCategoryDAO.queryShopCategoryByShopIDAndCategoryID(1, 2).get(0).getId())
        shopCategoryDAO.updateShopCategory(s)
        0 == shopCategoryDAO.queryShopCategoryByShopIDAndCategoryID(1, 2).get(0).getIsMain()

        cleanup:
        shopCategoryDAO.deleteShopCategoryByCategoryID(categoryID)
        shopCategoryDAO.deleteShopCategoryByShopID(shopID)
        shopCategoryDAO.deleteShopCategoryByShopIDAndCategoryID(shopID, categoryID)
    }
}
