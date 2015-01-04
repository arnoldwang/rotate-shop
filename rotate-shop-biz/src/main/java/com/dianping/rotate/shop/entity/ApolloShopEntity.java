package com.dianping.rotate.shop.entity;

/**
 * Created by luoming on 15/1/4.
 */
public class ApolloShopEntity {

    private int shopID;
    private int shopGroupID;
    private int cityID;
    private String district;
    private int shopType;
    private int status;

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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getShopType() {
        return shopType;
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
