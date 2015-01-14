package com.dianping.rotate.shop.service.impl.message.process;

import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.service.MessageProcessService;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.service.impl.ShopMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yangjie on 1/14/15.
 */
public abstract class Abstract implements MessageProcessService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageQueueDAO messageDAO;
    @Autowired
    protected ShopService shopService;
    @Autowired
    protected ShopMessageProducer shopMessageProducer;

}
