package com.dianping.rotate.shop.constants;

/**
 * Created by sharon on 15-3-13.
 */
public enum  AdApolloShopRatingEnum {
    COMMON_RECOMMENDED(101,"普通门店"),
    RECOMMENDED(102,"推荐门店"),
    INDIFFERENT_QUALITY(201,"推广通普通门店"),
    HIGH_QUALITY(202,"推广通优质门店");


    private int code;
    private String desc;

    private AdApolloShopRatingEnum(int code, String desc) {
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
        for (AdApolloShopRatingEnum adApolloShopRatingEnum : AdApolloShopRatingEnum.values()) {
            if (adApolloShopRatingEnum.getCode() == code) {
                result = adApolloShopRatingEnum.getDesc();
                break;
            }
        }
        return result;
    }
}
