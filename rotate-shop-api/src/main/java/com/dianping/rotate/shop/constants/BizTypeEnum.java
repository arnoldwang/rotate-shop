package com.dianping.rotate.shop.constants;

/**
 * User: zhenwei.wang
 * Date: 15-1-9
 */
public enum BizTypeEnum {
	JYPT(101, "交易平台销售业务"),
	DY(102, "全国电影业务"),
	JDLY(103, "旅游酒店业务"),
	TG(104, "推广业务"),
	JH(105, "结婚业务"),
	YD(106, "预订业务"),
	WM(107, "外卖业务");

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
