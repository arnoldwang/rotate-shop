package com.dianping.rotate.shop.service.message.processor

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.constants.MessageSource
import com.dianping.rotate.shop.constants.MessageStatus
import com.dianping.rotate.shop.constants.POIMessageType
import com.dianping.rotate.shop.dao.ApolloShopDAO
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO
import com.dianping.rotate.shop.dao.MessageQueueDAO
import com.dianping.rotate.shop.dao.RotateGroupDAO
import com.dianping.rotate.shop.json.ApolloShopEntity
import com.dianping.rotate.shop.json.ApolloShopExtendEntity
import com.dianping.rotate.shop.json.MessageEntity
import com.dianping.rotate.shop.service.impl.message.processor.ShopAddMessageProcessor
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by zaza on 15/1/19.
 */
class ShopAddMessageProcessorTest extends AbstractSpockTest{
    @Autowired
    private ShopAddMessageProcessor shopAddMessageProcessor;
    @Autowired
    private MessageQueueDAO messageQueueDAO;
    @Autowired
    private ApolloShopDAO apolloShopDAO;
    @Autowired
    private ApolloShopExtendDAO apolloShopExtendDAO;
    @Autowired
    private RotateGroupDAO rotateGroupDAO;

    private final static long swallowID = 99990123456789;
    private final static int shopId = 8877063;

    def "job process message for person add"(){
        setup:
        MessageEntity msg = new MessageEntity();
        msg.setSwallowId(swallowID);
        msg.setStatus(MessageStatus.NEW);
        msg.setSource(MessageSource.PERSON);
        msg.setAttemptIndex(0);
        msg.setMsg("{\"type\": 201,\"userId\": -12345,\"datetime\": \"2014-5-1\",\"pair\": {\"shopId\": 8877063,\"shopUrl\": \"http: //www.dianping.com/shop/12345\",\"shopName\": \"小肥羊\",\"branchName\": \"小肥羊\",\"altName\": \"XX\",\"address\": \"XXXX路\",\"crossRoad\": \"XXXXX\",\"businessHours\": \"4-5\",\"publicTransit\": \"XXXX\",\"priceInfo\": \"XXXXX\",\"userIP\": \"10.2.3.4\",\"msgStatus\": 0}}");
        msg.setType(POIMessageType.SHOP_ADD);
        messageQueueDAO.addToMessageQueue(msg);
        apolloShopDAO.deleteApolloShopPhysically(shopId);
        apolloShopExtendDAO.deleteApolloShopExtendByShopID(shopId);
        when:
        shopAddMessageProcessor.process(msg);
        then:
        MessageEntity msgRes = messageQueueDAO.getMessageBySwallowID(swallowID+"");
        msgRes.getStatus()==0;
        List<ApolloShopEntity> shops = apolloShopDAO.queryApolloShopByShopID(shopId);
        shops.get(0).getShopID()==shopId;
        List<ApolloShopExtendEntity> shopExtends = apolloShopExtendDAO.queryApolloShopExtendByShopID(shopId);
        shopExtends.get(0).getShopID()==shopId;
        cleanup:
        messageQueueDAO.deleteMessagePhysicallyBySwallowID(swallowID+"");
    }

    /*
    def "add test data"(){
        setup:
        MessageEntity msg = new MessageEntity();
        for(int i=500000 ;i<600000;i++){
            msg.setSwallowId(swallowID);
            msg.setStatus(MessageStatus.NEW);
            msg.setSource(MessageSource.PERSON);
            msg.setAttemptIndex(0);
            String msgStr1 = "{\"type\": 201,\"userId\": -12345,\"datetime\": \"2014-5-1\",\"pair\": {\"shopId\": ";
            String msgN = i+"";
            String msgStr2 = ",\"shopUrl\": \"http: //www.dianping.com/shop/12345\",\"shopName\": \"小肥羊\",\"branchName\": \"小肥羊\",\"altName\": \"XX\",\"address\": \"XXXX路\",\"crossRoad\": \"XXXXX\",\"businessHours\": \"4-5\",\"publicTransit\": \"XXXX\",\"priceInfo\": \"XXXXX\",\"userIP\": \"10.2.3.4\",\"msgStatus\": 0}}";
            String msgStr = msgStr1+msgN+msgStr2;
            msg.setMsg(msgStr);
            msg.setType(POIMessageType.SHOP_ADD);
            messageQueueDAO.addToMessageQueue(msg);
        }
    }
    */
}
