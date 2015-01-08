package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * Created by luoming on 15/1/6.
 */
public class ApolloShopDTO implements Serializable{

    private Integer shopID;
    private Integer shopGroupID;
    private Integer cityID;
    private Integer district;
    private Integer shopType;
    private Integer shopStatus;
    private Integer type;
    private Integer bizID;
    private String rating;
    private Integer shopExtendStatus;
    private Integer mainRegionID;
    private Integer mainCategoryID;

    public Integer getShopID() {
        return shopID;
    }

    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }

    public Integer getShopGroupID() {
        return shopGroupID;
    }

    public void setShopGroupID(Integer shopGroupID) {
        this.shopGroupID = shopGroupID;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBizID() {
        return bizID;
    }

    public void setBizID(Integer bizID) {
        this.bizID = bizID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getShopExtendStatus() {
        return shopExtendStatus;
    }

    public void setShopExtendStatus(Integer shopExtendStatus) {
        this.shopExtendStatus = shopExtendStatus;
    }

    public Integer getMainRegionID() {
        return mainRegionID;
    }

    public void setMainRegionID(Integer mainRegionID) {
        this.mainRegionID = mainRegionID;
    }

    public Integer getMainCategoryID() {
        return mainCategoryID;
    }

    public void setMainCategoryID(Integer mainCategoryID) {
        this.mainCategoryID = mainCategoryID;
    }
}
