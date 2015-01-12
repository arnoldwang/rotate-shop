package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.CategoryDTO;

import java.util.List;

/**
 * Created by luoming on 15/1/6.
 */
public interface CategoryService {

    public CategoryDTO getCategory(int categoryID, int cityID);

    public List<CategoryDTO> getCategoryAncestors(int categoryID, int cityID);

}
