package com.dianping.rotate.shop.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by luoming on 15/2/4.
 */
public class TaskDTO implements Serializable {

    private String startTime = "N/A";
    private String endTime = "N/A";
    private String runTime = "N/A";
    private Integer totalCount;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
