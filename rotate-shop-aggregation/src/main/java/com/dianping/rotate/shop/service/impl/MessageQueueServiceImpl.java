package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.service.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luoming on 15/1/30.
 */
@Service("messageQueueService")
public class MessageQueueServiceImpl implements MessageQueueService {

    @Autowired
    private MessageQueueDAO messageQueueDAO;

    @Transactional
    @Override
    public void clearMessageQueueToHistory() {
        messageQueueDAO.addMessageQueueToHistory();
        messageQueueDAO.clearMessageQueue();
    }
}
