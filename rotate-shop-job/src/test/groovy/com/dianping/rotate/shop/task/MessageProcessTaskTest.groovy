package com.dianping.rotate.shop.task

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.dao.ApolloShopDAO
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO
import com.dianping.rotate.shop.dao.MessageQueueDAO
import com.dianping.rotate.shop.dao.RotateGroupDAO
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by zaza on 15/1/19.
 */
class MessageProcessTaskTest extends AbstractSpockTest{
    @Autowired
    private MessageSingleProcessTask messageProcessTask;
    @Autowired
    private MessageQueueDAO messageQueueDAO;
    @Autowired
    private ApolloShopDAO apolloShopDAO;
    @Autowired
    private ApolloShopExtendDAO apolloShopExtendDAO;
    @Autowired
    private RotateGroupDAO rotateGroupDAO;

    private final static long swallowID = 99990123456789;


}
