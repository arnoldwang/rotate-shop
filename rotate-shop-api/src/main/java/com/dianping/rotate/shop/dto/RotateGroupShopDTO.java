package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public class RotateGroupShopDTO implements Serializable{
	private Integer rotateGroupID;

	private Integer shopID;

	private Integer shopGroupID;

	public Integer getRotateGroupID() {
		return rotateGroupID;
	}

	public void setRotateGroupID(Integer rotateGroupID) {
		this.rotateGroupID = rotateGroupID;
	}

	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}

	public Integer getShopGroupID() {
		return shopGroupID;
	}

	public void setShopGroupID(Integer shopGroupID) {
		this.shopGroupID = shopGroupID;
	}
}
