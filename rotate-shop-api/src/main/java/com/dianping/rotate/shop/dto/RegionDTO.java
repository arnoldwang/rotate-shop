package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * Created by luoming on 15/1/6.
 */
public class RegionDTO implements Serializable {

    private Integer regionID;
    private String regionName;
    private Integer cityID;
    private Integer regionType;

    public Integer getRegionID() {
        return regionID;
    }

    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }
}
