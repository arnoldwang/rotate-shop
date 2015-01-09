package com.dianping.rotate.shop.constants;

/**
 * User: zhenwei.wang
 * Date: 15-1-9
 */
public enum BizTypeEnum {
	TG_1(1, "团推"),
	S_2(2, "送"),
	D_3(3, "订"),
	T_4(4, "推"),
	TZ_5(5, "团座"),
	TH_6(6, "团惠"),
	TH_7(7, "团惠");

	private int code;
	private String desc;

	private BizTypeEnum(int code, String desc){
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
