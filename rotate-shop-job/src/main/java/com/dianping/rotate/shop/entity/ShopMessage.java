package com.dianping.rotate.shop.entity;

/**
 * Created by zaza on 15/1/14.
 */
public class ShopMessage {
    private int shopId;
    private int bizType;
    private String type;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getBizType() {
        return bizType;
    }

    public void setBizType(int bizType) {
        this.bizType = bizType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
