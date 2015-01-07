package com.dianping.rotate.shop.dto;

import java.io.Serializable;

/**
 * Created by luoming on 15/1/6.
 */
public class RegionDTO implements Serializable {

    private int regionID;
    private String regionName;
    private int cityID;
    private int regionType;

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getRegionType() {
        return regionType;
    }

    public void setRegionType(int regionType) {
        this.regionType = regionType;
    }
}
