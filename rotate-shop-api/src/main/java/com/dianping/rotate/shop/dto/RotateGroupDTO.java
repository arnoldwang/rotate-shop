package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public class RotateGroupDTO implements Serializable{
	private Integer bizID;

	private Integer type;

	public Integer getBizID() {
		return bizID;
	}

	public void setBizID(Integer bizID) {
		this.bizID = bizID;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
