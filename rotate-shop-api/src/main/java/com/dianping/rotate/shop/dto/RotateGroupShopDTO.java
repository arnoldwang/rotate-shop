package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public class RotateGroupShopDTO implements Serializable{
	private int rotateGroupID;

	private int shopID;

	private int shopGroupID;

	public int getRotateGroupID() {
		return rotateGroupID;
	}

	public void setRotateGroupID(int rotateGroupID) {
		this.rotateGroupID = rotateGroupID;
	}

	public int getShopID() {
		return shopID;
	}

	public void setShopID(int shopID) {
		this.shopID = shopID;
	}

	public int getShopGroupID() {
		return shopGroupID;
	}

	public void setShopGroupID(int shopGroupID) {
		this.shopGroupID = shopGroupID;
	}
}
