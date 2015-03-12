package com.dianping.rotate.shop.json;

import java.util.Date;

/**
 * User: zhenwei.wang
 * Date: 15-1-4
 */
public class RotateGroupShopEntity {

	private int id;

	private int rotateGroupID;

	private int shopID;

	private int shopGroupID;
	//状态：0，删除；1，正常
	private int status;

	private Date createdTime;

	private Date lastModifiedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
