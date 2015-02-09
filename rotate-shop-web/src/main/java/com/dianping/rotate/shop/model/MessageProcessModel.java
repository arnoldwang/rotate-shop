package com.dianping.rotate.shop.model;

/**
 * Created by zaza on 15/2/5.
 */
public class MessageProcessModel {
    private String messageName;
    private int messageType;
    private int messageSource;
    private int messageTotal;
    private int messageProcessSuccessTotal;
    private int messageProcessFailTotal;

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(int messageSource) {
        this.messageSource = messageSource;
    }

    public int getMessageTotal() {
        return messageTotal;
    }

    public void setMessageTotal(int messageTotal) {
        this.messageTotal = messageTotal;
    }

    public int getMessageProcessSuccessTotal() {
        return messageProcessSuccessTotal;
    }

    public void setMessageProcessSuccessTotal(int messageProcessSuccessTotal) {
        this.messageProcessSuccessTotal = messageProcessSuccessTotal;
    }

    public int getMessageProcessFailTotal() {
        return messageProcessFailTotal;
    }

    public void setMessageProcessFailTotal(int messageProcessFailTotal) {
        this.messageProcessFailTotal = messageProcessFailTotal;
    }
}
