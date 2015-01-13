package com.dianping.rotate.shop.constants;

/**
 * Created by luoming on 15/1/8.
 */
public enum RotateGroupTypeEnum {
    SINGLE(0, "单店"),
    CHAIN(1,"连锁店");

    private int code;
    private String desc;

    private RotateGroupTypeEnum(int code, String desc) {
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

    public static String getDescByCode(int code) {
        String result = "";
        for (RotateGroupTypeEnum apolloShopTypeEnum : RotateGroupTypeEnum.values()) {
            if (apolloShopTypeEnum.getCode() == code) {
                result = apolloShopTypeEnum.getDesc();
                break;
            }
        }
        return result;
    }
}
