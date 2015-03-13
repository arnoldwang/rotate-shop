package com.dianping.rotate.shop.constants;

/**
 * User: zhenwei.wang
 * Date: 15-2-5
 */
public enum ShopLogTypeEnum {
	INSERT_SHOP(1, "新增门店"),
	UPDATE_SHOP(2, "更新门店"),
	MERGE_SHOP(3, "合并门店"),
	SPLIT_SHOP(4, "拆分门店"),
	INSERT_SHOP_EXTEND(5, "新增门店扩展"),
	UPDATE_SHOP_EXTEND(6, "更新门店扩展"),
	INSERT_ROTATE_GROUP(7, "新增轮转组"),
	UPDATE_ROTATE_GROUP(8, "更新轮转组"),
	UPDATE_ROTATE_GROUP_SHOP(9, "更新门店轮转组关系");


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
