package com.dianping.rotate.shop.task

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.constants.MessageSource
import com.dianping.rotate.shop.constants.MessageStatus
import com.dianping.rotate.shop.constants.POIMessageType
import com.dianping.rotate.shop.dao.ApolloShopDAO
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO
import com.dianping.rotate.shop.dao.MessageQueueDAO
import com.dianping.rotate.shop.dao.RotateGroupDAO
import com.dianping.rotate.shop.json.MessageEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by zaza on 15/1/19.
 */
class MessageProcessTaskTest extends AbstractSpockTest{
    @Autowired
    private MessageProcessTask messageProcessTask;
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
