package com.dianping.rotate.shop.constants;

/**
 * Created by luoming on 15/1/8.
 */
public enum ApolloShopStatusEnum {
    OPEN(1, "正常"),
    CLOSE(2,"已关"),
    SUSPEND_BUSINESS(3, "暂停营业"),
    UN_BUSINESS(4,"尚未营业");

    private int code;
    private String desc;

    private ApolloShopStatusEnum(int code, String desc) {
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
        for (ApolloShopStatusEnum apolloShopStatusEnum : ApolloShopStatusEnum.values()) {
            if (apolloShopStatusEnum.getCode() == code) {
                result = apolloShopStatusEnum.getDesc();
                break;
            }
        }
        return result;
    }
}
