package com.dianping.rotate.shop.impl;

import com.dianping.combiz.entity.Category;
import com.dianping.rotate.shop.api.CategoryService;
import com.dianping.rotate.shop.dao.CategoryDAO;
import com.dianping.rotate.shop.dao.CategoryTreeDAO;
import com.dianping.rotate.shop.dto.CategoryDTO;
import com.dianping.rotate.shop.entity.CategoryEntity;
import com.dianping.rotate.shop.entity.CategoryTreeEntity;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoming on 15/1/7.
 */
@Service("apolloCategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private com.dianping.combiz.service.CategoryService categoryService;

    private Function<Category, CategoryDTO> toCategoryEntity = new Function<Category, CategoryDTO>() {
        @Override
        public CategoryDTO apply(Category category) {
            CategoryDTO categoryDTO = new CategoryDTO();
            if(category != null) {
                categoryDTO.setCityID(category.getCityId());
                categoryDTO.setCategoryID(category.getId());
                categoryDTO.setCategoryName(category.getName());
                categoryDTO.setCategoryType(category.getType());
            }
            return categoryDTO;
        }
    };
    
    @Override
    public CategoryDTO getCategory(int categoryID, int cityID) {
        return toCategoryEntity.apply(categoryService.loadCategory(cityID, categoryID));
    }

    @Override
    public List<CategoryDTO> getCategoryAncestors(int categoryID, int cityID) {
        // 先要看一下是否是合法的categoryID 和 cityID
        Category me = categoryService.loadCategory(cityID, categoryID);

        if (me == null) {
            return Lists.newArrayList();
        }

        List<Category> ret = categoryService.getMainCategoryPath(categoryID, cityID);

        // 如果是顶级分类的话，会返回一个长度是0的数组，这里就要把自己加进去
        if (ret.size() == 0) {
            ret.add(me);
        }

        return Lists.transform(ret, toCategoryEntity);
    }
}
