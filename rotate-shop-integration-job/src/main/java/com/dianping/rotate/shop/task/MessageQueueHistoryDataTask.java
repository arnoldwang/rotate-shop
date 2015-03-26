package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.service.MessageQueueService;
import com.dianping.rotate.shop.utils.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.unidal.dal.jdbc.annotation.Attribute;

/**
 * Created by luoming on 15/1/30.
 */
public class MessageQueueHistoryDataTask {

    @Autowired
    private MessageQueueService messageQueueService;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void process() {
        try {
            if (!ConfigUtils.getMessageQueueHistoryDataTaskTrigger()) {
                logger.info("MessageQueueHistoryDataTask will not run!");
                return;
            }

            long start = System.currentTimeMillis();
            logger.info("MessageQueueHistoryDataTask start");
            messageQueueService.clearMessageQueueToHistory();
            logger.info("MessageQueueHistoryDataTask end("+(System.currentTimeMillis()-start)+"ms)");
        } catch(Exception e) {
            logger.error("MessageQueueHistoryDataTask fail", e);
        }
    }

}
