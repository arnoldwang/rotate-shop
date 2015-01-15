package com.dianping.rotate.shop.dao

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.json.MessageEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by zaza on 15/1/5.
 */
class MessageQueueDAOTest extends AbstractSpockTest{
    @Autowired
    MessageQueueDAO messageQueueDAO;

    def "test add message queue"(){
        setup:
        def message = new MessageEntity();
        message.setAttemptIndex(1);
        message.setMsg("groovy message queue test");
        message.setSource(1);
        message.setStatus(1);
        message.setSwallowId("0ea12334swallow");

        when:
        messageQueueDAO.addToMessageQueue(message);
        MessageEntity msg = messageQueueDAO.getMessageById(1);

        then:
        msg.getMsg().equals("groovy message queue test");


        cleanup:
        messageQueueDAO.deleteMessage(2);

    }
}
