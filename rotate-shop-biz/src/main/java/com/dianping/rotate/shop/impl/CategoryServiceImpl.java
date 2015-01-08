package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.CategoryService;
import com.dianping.rotate.shop.dao.CategoryDAO;
import com.dianping.rotate.shop.dao.CategoryTreeDAO;
import com.dianping.rotate.shop.dto.CategoryDTO;
import com.dianping.rotate.shop.entity.CategoryEntity;
import com.dianping.rotate.shop.entity.CategoryTreeEntity;
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
    private CategoryDAO categoryDAO;

    @Autowired
    private CategoryTreeDAO categoryTreeDAO;
    
    @Override
    public CategoryDTO getCategoryByCategoryIDAndCityID(int categoryID, int cityID) {
        return transCategoryEntityToDTO(getCategoryEntityByCategoryIDAndCityID(categoryID, cityID));
    }

    @Override
    public List<CategoryDTO> getCategoryTreeByCategoryIDAndCityID(int categoryID, int cityID) {
        List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
        CategoryEntity category = getCategoryEntityByCategoryIDAndCityID(categoryID, cityID);
        getParentCategoryByCategory(categoryDTOList, category);
        return categoryDTOList;
    }

    private CategoryEntity getCategoryEntityByCategoryIDAndCityID(int categoryID, int cityID) {
        List<CategoryEntity> categoryList = categoryDAO.queryCategoryByCategoryIDAndCityID(categoryID, cityID);
        if(categoryList != null && categoryList.size() != 0) {
            return categoryList.get(0);
        }
        return null;
    }

    private CategoryDTO transCategoryEntityToDTO(CategoryEntity category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        if(category != null) {
            categoryDTO.setCityID(category.getCityID());
            categoryDTO.setCategoryID(category.getCategoryID());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTO.setCategoryType(category.getCategoryType());
        }
        return categoryDTO;
    }

    private void getParentCategoryByCategory(List<CategoryDTO> categoryDTOList, CategoryEntity category) {
        if(category != null) {
            categoryDTOList.add(0, transCategoryEntityToDTO(category));
            List<CategoryTreeEntity> categoryTreeList = categoryTreeDAO.queryMainCategoryTreeByCategoryIDAndCityID(category.getCategoryID(), category.getCityID());
            if(categoryTreeList != null && categoryTreeList.size() != 0) {
                CategoryTreeEntity categoryTree = categoryTreeList.get(0);
                getParentCategoryByCategory(categoryDTOList,
                        getCategoryEntityByCategoryIDAndCityID(categoryTree.getParentID(), categoryTree.getCityID()));
            }
        }
    }

}
