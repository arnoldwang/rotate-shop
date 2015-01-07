package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * Created by luoming on 15/1/6.
 */
public class CategoryDTO implements Serializable {

    private int categoryID;
    private String categoryName;
    private int cityID;
    private int categoryType;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }
}
