package com.dianping.rotate.shop.json;

import java.util.Date;

/**
 * User: luoming
 * Date: 15-2-4
 */
public class TaskEntity {
	private Date startTime;
	private Date endTime;
	private Long runTime;
	private int totalCount;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getRunTime() {
		return runTime;
	}

	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
