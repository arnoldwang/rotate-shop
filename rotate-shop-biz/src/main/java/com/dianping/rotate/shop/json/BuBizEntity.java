package com.dianping.rotate.shop.json;

import java.util.Date;

/**
 * User: zhenwei.wang
 * Date: 15-1-5
 */
public class BuBizEntity {
	private int id;

	private int buID;

	private int bizID;

	private int status;

	private Date createdTime;

	private Date lastModifiedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBuID() {
		return buID;
	}

	public void setBuID(int buID) {
		this.buID = buID;
	}

	public int getBizID() {
		return bizID;
	}

	public void setBizID(int bizID) {
		this.bizID = bizID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
}
