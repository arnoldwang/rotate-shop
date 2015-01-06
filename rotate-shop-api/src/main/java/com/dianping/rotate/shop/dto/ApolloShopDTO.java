package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * Created by luoming on 15/1/6.
 */
public class ApolloShopDTO implements Serializable{

    private int shopID;
    private int shopGroupID;
    private int cityID;
    private int district;
    private int shopType;
    private int shopStatus;
    private int type;
    private int bizID;
    private String rating;
    private int shopExtendStatus;
    private int mainRegionID;
    private int mainCategoryID;

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public int getShopGroupID() {
        return shopGroupID;
    }

    public void setShopGroupID(int shopGroupID) {
        this.shopGroupID = shopGroupID;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getShopType() {
        return shopType;
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;
    }

    public int getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(int shopStatus) {
        this.shopStatus = shopStatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBizID() {
        return bizID;
    }

    public void setBizID(int bizID) {
        this.bizID = bizID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getShopExtendStatus() {
        return shopExtendStatus;
    }

    public void setShopExtendStatus(int shopExtendStatus) {
        this.shopExtendStatus = shopExtendStatus;
    }

    public int getMainRegionID() {
        return mainRegionID;
    }

    public void setMainRegionID(int mainRegionID) {
        this.mainRegionID = mainRegionID;
    }

    public int getMainCategoryID() {
        return mainCategoryID;
    }

    public void setMainCategoryID(int mainCategoryID) {
        this.mainCategoryID = mainCategoryID;
    }
}
