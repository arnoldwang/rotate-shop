package com.dianping.rotate.shop.constants;

/**
 * User: zhenwei.wang
 * Date: 15-2-5
 */
public enum ShopLogTypeEnum {
	INSERT(1, "新增"),
	UPDATE(2, "更新"),
	MERGE(3, "合并"),
	SPLIT(4, "拆分");

	private int code;
	private String desc;

	private ShopLogTypeEnum(int code, String desc){
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
