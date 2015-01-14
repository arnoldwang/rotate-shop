package com.dianping.rotate.shop.constants;

/**
 * Created by luoming on 15/1/8.
 */
public enum RotateGroupCustomerStatusEnum {
    COMMON(0, "普通客户"),
    VIP(1,"大客户");

    private int code;
    private String desc;

    private RotateGroupCustomerStatusEnum(int code, String desc) {
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
        for (RotateGroupCustomerStatusEnum apolloShopTypeEnum : RotateGroupCustomerStatusEnum.values()) {
            if (apolloShopTypeEnum.getCode() == code) {
                result = apolloShopTypeEnum.getDesc();
                break;
            }
        }
        return result;
    }
}
