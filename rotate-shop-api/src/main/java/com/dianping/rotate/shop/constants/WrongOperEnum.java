package com.dianping.rotate.shop.constants;

/**
 * Created by luoming on 15/3/13.
 */
public enum WrongOperEnum {

    SHOP_ROTATEGROUP_MERGE(1, "门店无法确认合并到哪个轮转组");

    private int code;
    private String desc;

    private WrongOperEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
