package com.dianping.rotate.shop.listener

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.constants.MessageSource
import com.dianping.rotate.shop.constants.POIMessageType
import com.dianping.rotate.shop.dao.MessageQueueDAO
import com.dianping.rotate.shop.json.MessageEntity
import com.dianping.swallow.common.internal.message.SwallowMessage
import com.dianping.swallow.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by zaza on 15/1/19.
 */
class POIAddListenerTest extends AbstractSpockTest{
    @Autowired
    POIAddListener poiAddListener;
    @Autowired
    MessageQueueDAO messageQueueDAO;

    private final static long swallowID = 66660123456789;

    def "accept poi add  message for type = 201"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"type\": 201,\"userId\": -12345,\"datetime\": \"2014-5-1\",\"pair\": [{\"shopId\": 5000010,\"shopUrl\": \"http: //www.dianping.com/shop/12345\",\"shopName\": \"小肥羊\",\"branchName\": \"小肥羊\",\"altName\": \"XX\",\"address\": \"XXXX路\",\"crossRoad\": \"XXXXX\",\"businessHours\": \"4-5\",\"publicTransit\": \"XXXX\",\"priceInfo\": \"XXXXX\",\"userIP\": \"10.2.3.4\",\"msgStatus\": 0}]}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiAddListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgEntity.getSwallowId().equals(swallowID);
        msgEntity.getSource()== POIMessageType.SHOP_ADD;
        msgEntity.getSource()== MessageSource.PERSON;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    def "accept poi add  message for type != 201"(){
        setup:
        Message msg = new SwallowMessage();
        String msgStr = "{\"type\": 200,\"userId\": -12345,\"datetime\": \"2014-5-1\",\"pair\": [{\"shopId\": 5000010,\"shopUrl\": \"http: //www.dianping.com/shop/12345\",\"shopName\": \"小肥羊\",\"branchName\": \"小肥羊\",\"altName\": \"XX\",\"address\": \"XXXX路\",\"crossRoad\": \"XXXXX\",\"businessHours\": \"4-5\",\"publicTransit\": \"XXXX\",\"priceInfo\": \"XXXXX\",\"userIP\": \"10.2.3.4\",\"msgStatus\": 0}]}";
        msg.setContent(msgStr);
        msg.setMessageId(swallowID);
        when:
        poiAddListener.onMessage(msg);
        then:
        MessageEntity msgEntity = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        null == msgEntity;
    }

}
