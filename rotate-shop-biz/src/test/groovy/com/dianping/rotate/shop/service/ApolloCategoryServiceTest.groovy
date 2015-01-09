package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.api.CategoryService
import com.dianping.rotate.shop.dto.CategoryDTO
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by yangjie on 1/9/15.
 */
class ApolloCategoryServiceTest extends AbstractSpockTest {
    @Autowired
    CategoryService categoryService

    private final int c1 = 10;
    private final int c2 = 101;
    private final int c3 = 198;

    private final int shanghai = 1;

    def "get category"() {
        when:
        CategoryDTO dto = categoryService.getCategory(c1, shanghai)

        then:
        dto.getCategoryID() == c1;
    }

    def "get ancestors for c1"() {
        when:
        List<CategoryDTO> dtos = categoryService.getCategoryAncestors(c1, shanghai);

        then:
        dtos.size() == 1;
    }


    def "get ancestors for c2"() {
        when:
        List<CategoryDTO> dtos = categoryService.getCategoryAncestors(c2, shanghai);

        then:
        dtos.size() == 2;
    }


    def "get ancestors for c3"() {
        when:
        List<CategoryDTO> dtos = categoryService.getCategoryAncestors(c3, shanghai);

        then:
        dtos.size() == 3;
    }
}
