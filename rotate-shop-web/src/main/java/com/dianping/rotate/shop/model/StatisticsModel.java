package com.dianping.rotate.shop.model;

import java.util.List;

/**
 * Created by zaza on 15/2/4.
 */
public class StatisticsModel {
    private int total;
    private List<MessageProcessModel> messageStatisticsList;
    private List<MessageModel> messageList;

    public StatisticsModel(){}

    public StatisticsModel(List<MessageModel> messageList){
        this.messageList = messageList;
    }

    public List<MessageProcessModel> getMessageStatisticsList() {
        return messageStatisticsList;
    }

    public void setMessageStatisticsList(List<MessageProcessModel> messageStatisticsList) {
        this.messageStatisticsList = messageStatisticsList;
    }

    public List<MessageModel> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageModel> messageList) {
        this.messageList = messageList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
