package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.service.MessageProcessor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zaza on 15/1/20.
 */
public abstract class AbstractMessageRunner implements Runnable{
    protected ExecutorService threadPool = Executors.newFixedThreadPool(10);
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageQueueDAO messageQueueDAO;

    protected void runMessages(List<MessageEntity> messages) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(messages.size());
        for (final MessageEntity message : messages) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        getMessageProcessor().process(message);
                        markMessageHasDone(message);
                    } catch (Exception e) {
                        logger.error("Process message error :" + ToStringBuilder.reflectionToString(message,
                                ToStringStyle.SHORT_PREFIX_STYLE), e);
                        try {
                            markMessageHasFailed(message);
                        } catch (Exception e1) {
                            logger.error("MarkMessageHasFailed error :" + ToStringBuilder.reflectionToString(message,
                                    ToStringStyle.SHORT_PREFIX_STYLE), e);
                        }
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await(100, TimeUnit.SECONDS);
    }

    private void markMessageHasDone(MessageEntity msg) {
        messageQueueDAO.deleteMessage(msg.getId());
    }

    private void markMessageHasFailed(MessageEntity msg) {
        messageQueueDAO.updateMessageAttemptIndex(msg.getId(),msg.getAttemptIndex()+1);
    }

    abstract int getMessageSourceType();
    abstract int getPOIMessageType();
    abstract MessageProcessor getMessageProcessor();
}
