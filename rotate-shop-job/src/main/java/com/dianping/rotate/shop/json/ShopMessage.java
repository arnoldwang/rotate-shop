package com.dianping.rotate.shop.json;

/**
 * Created by zaza on 15/1/14.
 */

/*
  Topic：dp_apollo_shop_change
  Json消息体
  {
    "shopId": 3454393,
    "type":"insert"
    }
*/
public class ShopMessage {
    private int shopId;
    private String type;

    public ShopMessage(){}

    public ShopMessage(int shopId,String type){
        this.shopId = shopId;
        this.type = type;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
