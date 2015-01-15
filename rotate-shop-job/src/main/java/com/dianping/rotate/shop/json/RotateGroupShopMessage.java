package com.dianping.rotate.shop.json;

/**
 * Created by zaza on 15/1/15.
 */

/*
Topic:dp_apollo_rotategroupshop_change
消息体格式：
{
    "rotateGroupId": 3454393,
    "type" : "update"
}
*/
public class RotateGroupShopMessage {
    public RotateGroupShopMessage(){}
    public RotateGroupShopMessage(int rotateGroupId,String type){
        this.rotateGroupId = rotateGroupId;
        this.type = type;
    }
    private int rotateGroupId;
    private String type;

    public int getRotateGroupId() {
        return rotateGroupId;
    }

    public void setRotateGroupId(int rotateGroupId) {
        this.rotateGroupId = rotateGroupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
