package com.dianping.rotate.shop.entity;

import java.util.Date;

/**
 * Created by zaza on 15/1/4.
 */
public class MessageEntity {
    private int id;
    private String msg;
    private String swallowId;
    private int attemptIndex;
    private int source;
    private int status;

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

    public String getSwallowId() {
        return swallowId;
    }

    public void setSwallowId(String swallowId) {
        this.swallowId = swallowId;
    }

    public int getAttemptIndex() {
        return attemptIndex;
    }

    public void setAttemptIndex(int attemptIndex) {
        this.attemptIndex = attemptIndex;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
