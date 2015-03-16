package com.dianping.rotate.shop.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public class RotateGroupDTO implements Serializable{
	private Integer id;

	private Integer bizID;

	private Integer type;

	private Integer status;

	private Integer cooperationStatus;

	private Date minOfflineTime;

	private Date maxOfflineTime;

	private Integer customerStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCooperationStatus() {
		return cooperationStatus;
	}

	public Date getMinOfflineTime() {
		return minOfflineTime;
	}

	public void setMinOfflineTime(Date minOfflineTime) {
		this.minOfflineTime = minOfflineTime;
	}

	public Date getMaxOfflineTime() {
		return maxOfflineTime;
	}

	public void setMaxOfflineTime(Date maxOfflineTime) {
		this.maxOfflineTime = maxOfflineTime;
	}

	public void setCooperationStatus(Integer cooperationStatus) {
		this.cooperationStatus = cooperationStatus;
	}

	public Integer getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(Integer customerStatus) {
		this.customerStatus = customerStatus;
	}
}
