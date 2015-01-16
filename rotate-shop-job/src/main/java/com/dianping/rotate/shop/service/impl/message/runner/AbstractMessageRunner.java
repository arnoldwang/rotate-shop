package com.dianping.rotate.shop.service.impl.message.runner;

import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.service.impl.message.producer.ShopMessageProducer;
import com.dianping.rotate.shop.utils.Switch;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yangjie on 1/14/15.
 */
public abstract class AbstractMessageRunner implements Runnable {
	private static final int PROCESS_MESSAGE_LIMIT = 10;
	public static final int INTERVAL_WHEN_NO_TASK = 100;
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);
	protected Logger logger = LoggerFactory.getLogger(getClass());
    private static final int MAX_RETRY = 10;

    abstract int getMessageSourceType();
    abstract int getPOIMessageType();
    abstract void doMessage(MessageEntity message);

    protected void markMessageHasDone(MessageEntity msg) {
        messageDAO.deleteMessage(msg.getId());
    }

    protected void markMessageHasFailed(MessageEntity msg) {
        messageDAO.updateMessageAttemptIndex(msg.getId(),msg.getAttemptIndex()+1);
    }

    protected void publishMessageToMQ(Object msg) {
        shopMessageProducer.send(msg);
    }

    @Autowired
    private ShopMessageProducer shopMessageProducer;

    @Autowired
    private MessageQueueDAO messageQueueDAO;

    @Autowired
    private MessageQueueDAO messageDAO;

    @Override
    public void run() {
        while(true){
            try {
                if(Switch.on()){
					List<MessageEntity> messages = messageQueueDAO.getUnproccessedMessage(getMessageSourceType(),
							getPOIMessageType(),
							MAX_RETRY, PROCESS_MESSAGE_LIMIT);
					if (messages.size() == 0) {
						Thread.sleep(INTERVAL_WHEN_NO_TASK);
					} else {
						runMessages(messages);
					}
				}
            }catch(Exception ex){
                logger.error(ex.getMessage(), ex);
            }
        }
    }

	private void runMessages(List<MessageEntity> messages) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(messages.size());
		for (final MessageEntity message : messages) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						doMessage(message);
					} catch (Exception e) {
						logger.error("Process message error " + ToStringBuilder.reflectionToString(message,
								ToStringStyle.SHORT_PREFIX_STYLE), e);
					} finally {
						countDownLatch.countDown();
					}
				}
			});
		}
		countDownLatch.await(100, TimeUnit.SECONDS);
	}

}
