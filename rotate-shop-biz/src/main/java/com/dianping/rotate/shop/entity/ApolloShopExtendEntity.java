package com.dianping.rotate.shop.entity;

/**
 * Created by luoming on 15/1/4.
 */
public class ApolloShopExtendEntity {

    private int id;
    private int shopID;
    private int type;
    private int bizID;
    private String rating;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
