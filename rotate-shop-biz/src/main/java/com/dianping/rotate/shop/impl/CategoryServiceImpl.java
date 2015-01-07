package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.CategoryService;
import com.dianping.rotate.shop.dao.CategoryDAO;
import com.dianping.rotate.shop.dto.CategoryDTO;
import com.dianping.rotate.shop.dto.CategoryDTO;
import com.dianping.rotate.shop.entity.CategoryEntity;
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
    
    @Override
    public CategoryDTO getCategoryByCategoryIDAndCityID(int categoryID, int cityID) {
        List<CategoryEntity> categoryList = categoryDAO.queryCategoryByCategoryIDAndCityID(categoryID, cityID);
        if(categoryList != null && categoryList.size() != 0) {
            return transCategoryEntityToDTO(categoryList.get(0));
        }
        return null;
    }

    @Override
    public List<CategoryDTO> getCategoryTreeByCategoryIDAndCityID(int categoryID, int cityID) {
        List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
        List<CategoryEntity> categoryList = categoryDAO.queryCategoryByCategoryIDAndCityID(categoryID, cityID);
        if(categoryList != null && categoryList.size() != 0) {
            CategoryEntity category = categoryList.get(0);

        }
        return null;
    }

    private CategoryDTO transCategoryEntityToDTO(CategoryEntity category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCityID(category.getCityID());
        categoryDTO.setCategoryID(category.getCategoryID());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setCategoryType(category.getCategoryType());
        return categoryDTO;
    }

}
