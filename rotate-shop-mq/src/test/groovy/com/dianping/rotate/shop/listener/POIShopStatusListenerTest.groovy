package com.dianping.rotate.shop.listener

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.constants.MessageSource
import com.dianping.rotate.shop.constants.POIMessageType
import com.dianping.rotate.shop.dao.MessageQueueDAO
import com.dianping.rotate.shop.json.MessageEntity
import com.dianping.swallow.common.internal.message.SwallowMessage
import com.dianping.swallow.common.message.Message
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by zaza on 15/1/19.
 */
class POIShopStatusListenerTest extends AbstractSpockTest{
    @Autowired
    POIShopStatusListener poiShopStatusListener;
    @Autowired
    MessageQueueDAO messageQueueDAO;

    private final static long swallowID = 88880123456789;

    def "accept poi shop status message for person"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"shopId\": 3454393,\"lastModifyUser\": -1234,\"displayStatus\":1,\"businessStatus\":2}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiShopStatusListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_STATUS;
        msgEntity.getSource()== MessageSource.PERSON;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    def "accept poi shop status message for system"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"shopId\": 3454393,\"lastModifyUser\": -15900,\"displayStatus\":1,\"businessStatus\":2}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiShopStatusListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_STATUS;
        msgEntity.getSource()== MessageSource.SYSTEM;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }
}
