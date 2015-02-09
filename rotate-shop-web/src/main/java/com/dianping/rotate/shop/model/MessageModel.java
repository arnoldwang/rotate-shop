package com.dianping.rotate.shop.model;

/**
 * Created by zaza on 15/2/4.
 */
public class MessageModel {
    private int id;
    private String msg;
    private int retry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }
}
