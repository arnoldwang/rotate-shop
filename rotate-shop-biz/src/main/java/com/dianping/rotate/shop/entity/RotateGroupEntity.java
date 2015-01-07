package com.dianping.rotate.shop.entity;

import java.util.Date;

/**
 * User: zhenwei.wang
 * Date: 15-1-4
 */
public class RotateGroupEntity {
	private int id;

	private int bizID;
	//类型：0，单店；1，连锁店
	private int type;
	//状态：0，删除；1，正常；2，关闭；3，暂停营业；4，尚未营业；
	private int status;

	private Date createdTime;

	private Date lastModifiedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
