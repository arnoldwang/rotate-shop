package com.dianping.rotate.shop.json;

import java.util.List;

/**
 * Created by zaza on 15/3/16.
 */
public class RotateGroupShopJson {
    String type;
    RotateGroupOwnerJson rotateGroup;
    List<ShopJson> shop;
    RotateGroupOwnerJson oldRotateGroup;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RotateGroupOwnerJson getRotateGroup() {
        return rotateGroup;
    }

    public void setRotateGroup(RotateGroupOwnerJson rotateGroup) {
        this.rotateGroup = rotateGroup;
    }

    public List<ShopJson> getShop() {
        return shop;
    }

    public void setShop(List<ShopJson> shop) {
        this.shop = shop;
    }

    public RotateGroupOwnerJson getOldRotateGroup() {
        return oldRotateGroup;
    }

    public void setOldRotateGroup(RotateGroupOwnerJson oldRotateGroup) {
        this.oldRotateGroup = oldRotateGroup;
    }
}
