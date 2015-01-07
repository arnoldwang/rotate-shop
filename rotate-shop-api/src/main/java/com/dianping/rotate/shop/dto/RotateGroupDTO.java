package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public class RotateGroupDTO implements Serializable{
	private int bizID;

	private int type;

	public int getBizID() {
		return bizID;
	}

	public void setBizID(int bizID) {
		this.bizID = bizID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
