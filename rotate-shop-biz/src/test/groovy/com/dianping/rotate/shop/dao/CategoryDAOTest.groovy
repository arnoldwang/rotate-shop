package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.CategoryEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by luoming on 15/1/4.
 */
class CategoryDAOTest extends AbstractSpockTest {
    @Autowired
    CategoryDAO categoryDAO;

    def "add, query and delete one category"() {
        setup:
        int categoryID = 1

        when:
        def s = new CategoryEntity()
        s.setCategoryID(categoryID)
        s.setCategoryName('上海')
        s.setStatus(1)
        categoryDAO.addCategory(s)

        then:
        1 == categoryDAO.queryCategoryByCategoryID(categoryID).get(0).getCategoryID()
        s.setCategoryName('北京')
        categoryDAO.updateCategory(s)
        '北京'.equals(categoryDAO.queryCategoryByCategoryID(categoryID).get(0).getCategoryName())

        cleanup:
        categoryDAO.deleteCategoryByCategoryID(categoryID)
    }
}
