package com.dianping.rotate.shop.constants;

/**
 * Created by luoming on 15/1/8.
 */
public enum RotateShopCooperationStatusEnum {
    OFFLINE(0, "已下线"),
    ONLINE(1,"在线");

    private int code;
    private String desc;

    private RotateShopCooperationStatusEnum(int code, String desc) {
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
        for (RotateShopCooperationStatusEnum apolloShopStatusEnum : RotateShopCooperationStatusEnum.values()) {
            if (apolloShopStatusEnum.getCode() == code) {
                result = apolloShopStatusEnum.getDesc();
                break;
            }
        }
        return result;
    }
}
