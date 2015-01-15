package com.dianping.rotate.shop.json;

import java.sql.Date;

/**
 * Created by luoming on 15/1/4.
 */
public class ApolloShopBusinessStatusEntity {

    private int id;
    private int shopID;
    private int cooperationStatus;
    private Date offlineDate;
    private int businessType;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public int getCooperationStatus() {
        return cooperationStatus;
    }

    public void setCooperationStatus(int cooperationStatus) {
        this.cooperationStatus = cooperationStatus;
    }

    public Date getOfflineDate() {
        return offlineDate;
    }

    public void setOfflineDate(Date offlineDate) {
        this.offlineDate = offlineDate;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
