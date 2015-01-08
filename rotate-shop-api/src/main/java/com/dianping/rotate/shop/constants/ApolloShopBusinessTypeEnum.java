package com.dianping.rotate.shop.constants;

/**
 * Created by luoming on 15/1/8.
 */
public enum ApolloShopBusinessTypeEnum {
    TUAN_GOU(0, "团购"),
    TUI_GUANG(1, "推广"),
    HUI_YUAN_KA(2, "会员卡"),
    CHONG_ZHI_KA(4,"充值卡"),
    YU_DING(5,"外卖"),
    WAI_MAI(6,"外卖"),
    SHAN_HUI(7,"闪惠");

    private int code;
    private String desc;

    private ApolloShopBusinessTypeEnum(int code, String desc) {
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
        for (ApolloShopBusinessTypeEnum apolloShopBusinessTypeEnum : ApolloShopBusinessTypeEnum.values()) {
            if (apolloShopBusinessTypeEnum.getCode() == code) {
                result = apolloShopBusinessTypeEnum.getDesc();
                break;
            }
        }
        return result;
    }
}
