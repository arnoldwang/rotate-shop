package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.entity.MessageEntity;

/**
 * Created by yangjie on 1/14/15.
 */
public interface MessageProcessService {
    public void process(int type, MessageEntity msg);
}
