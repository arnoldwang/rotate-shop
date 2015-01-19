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
class POIUChangeListenerTest extends AbstractSpockTest{
    @Autowired
    POIChangeListener poiChangeListener;
    @Autowired
    MessageQueueDAO messageQueueDAO;

    private final static long swallowID = 77770123456789;

    def "accept poi change message for system batch add"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"shopId\": 3454393,\"messageType\": 4}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiChangeListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_ADD_BATCH;
        msgEntity.getSource()== MessageSource.SYSTEM;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    def "accept poi change message for person update"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"shopId\": 3454393,\"lastModifyUser\": -1234,\"messageType\": 5}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiChangeListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_UPDATE;
        msgEntity.getSource()== MessageSource.PERSON;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    def "accept poi change message for system update"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"shopId\": 3454393,\"lastModifyUser\": -15900,\"messageType\": 5}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiChangeListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_UPDATE;
        msgEntity.getSource()== MessageSource.SYSTEM;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    def "accept poi change message for merge"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"messageType\": 2,\"ShopID\": 123456,\"ToShopID\": 234567}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiChangeListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_MERGE;
        msgEntity.getSource()== MessageSource.PERSON;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    def "accept poi change message for merge recover"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{ \"messageType\": 3,\"ShopID\": 123456,\"RestoreShopID\": 234567}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiChangeListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_MERGE_RECOVER;
        msgEntity.getSource()== MessageSource.PERSON;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    def "accept poi change message for region"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"messageType\": 1,\"actionType\": \"add\",\"id\": 123}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiChangeListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_REGION;
        msgEntity.getSource()== MessageSource.PERSON;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    def "accept poi change message for category"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"messageType\": 6,\"actionType\": \"add\",\"id\": 123, \"cityId\": 123}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiChangeListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getType()== POIMessageType.SHOP_CATEGORY;
        msgEntity.getSource()== MessageSource.PERSON;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

}
