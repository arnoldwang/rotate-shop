package com.dianping.rotate.shop.json;

/**
 * Created by luoming on 15/1/4.
 */
public class ApolloShopEntity {

    private int id;
    private int shopID;
    private int shopStatus;
    private int shopGroupID;
    private int cityID;
    private int district;
    private int shopType;

    public int getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(int shopStatus) {
        this.shopStatus = shopStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

}
