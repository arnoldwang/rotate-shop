package com.dianping.rotate.shop.json;

/**
 * Created by zaza on 15/3/16.
 */
public class RotateGroupOwnerJson {
    Integer rotateGroupId;
    Integer owner;

    public RotateGroupOwnerJson(){

    }

    public RotateGroupOwnerJson(Integer rotateGroupId,Integer owner){
        this.rotateGroupId = rotateGroupId;
        this.owner = owner;
    }

    public Integer getRotateGroupId() {
        return rotateGroupId;
    }

    public void setRotateGroupId(Integer rotateGroupId) {
        this.rotateGroupId = rotateGroupId;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}
