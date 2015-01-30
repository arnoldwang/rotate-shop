package com.dianping.rotate.shop.constants;

/**
 * Created by luoming on 15/1/8.
 */
public enum RotateShopCooperationStatusEnum {
    UNKNOW(-1, "未知"),
    NO_COOP(0, "未合作"),
    COOPING(1,"合作中"),
    COOP_BREAK(2, "断约");

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
        for (RotateShopCooperationStatusEnum rotateShopCooperationStatusEnum : RotateShopCooperationStatusEnum.values()) {
            if (rotateShopCooperationStatusEnum.getCode() == code) {
                result = rotateShopCooperationStatusEnum.getDesc();
                break;
            }
        }
        return result;
    }
}
