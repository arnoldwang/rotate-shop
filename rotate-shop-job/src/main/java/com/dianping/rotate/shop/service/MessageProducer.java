package com.dianping.rotate.shop.service;

/**
 * Created by zaza on 15/1/14.
 */
public interface MessageProducer {
    public void send(int shopId,int bizType,String action);

}
