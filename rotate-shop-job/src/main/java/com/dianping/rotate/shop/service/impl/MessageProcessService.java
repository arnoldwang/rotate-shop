package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.swallow.common.internal.dao.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/8.
 */
public class MessageProcessService {
    @Autowired
    MessageDAO messageDAO;

    public Integer messageProcess(MessageEntity msg){
        return 0;
    }
}
