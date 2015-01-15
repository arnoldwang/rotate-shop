package com.dianping.rotate.shop.factory;

import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.MessageStatus;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.swallow.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/5.
 */
public class POIMergeRecover  implements POIChange{
    @Autowired
    MessageQueueDAO messageQueueDAO;

    @Override
    public void addToMsgQueue(Message msg){
        MessageEntity msgEntity = new MessageEntity();
        msgEntity.setAttemptIndex(0);
        msgEntity.setMsg(msg.getContent());
        msgEntity.setSource(MessageSource.PERSON);
        msgEntity.setStatus(MessageStatus.NEW);
        msgEntity.setSwallowId(msg.getMessageId());
        msgEntity.setType(POIMessageType.SHOP_MERGE_RECOVER);
        messageQueueDAO.addToMessageQueue(msgEntity);
    }
}
