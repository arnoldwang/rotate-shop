package com.dianping.rotate.shop.constants;

/**
 * Created by luoming on 15/1/8.
 */
public enum ApolloShopStatusEnum {
    ONLY_PHONE(0, "只在手机端显示"),
    CLOSE(1,"已关"),
    SUSPEND_BUSINESS(2, "暂停营业"),
    NOT_ACTIVE(3,"非active"),
    UN_BUSINESS(4,"尚未营业"),
    OPEN(5, "正常"),
    INTEGRAL_MERCHANT(10, "积分商户");

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
