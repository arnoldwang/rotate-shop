package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.CategoryDTO;

/**
 * Created by luoming on 15/1/6.
 */
public interface CategoryService {

    public CategoryDTO getCategoryByCategoryIDAndCityID(int categoryID, int cityID);

}
