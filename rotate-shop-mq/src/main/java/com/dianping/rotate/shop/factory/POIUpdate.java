package com.dianping.rotate.shop.factory;
import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.rotate.shop.constants.MessageSource;
import com.dianping.rotate.shop.constants.MessageStatus;
import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.service.POIChangeService;
import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.swallow.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/5.
 */
public class POIUpdate implements POIChange{

    @Autowired
    MessageQueueDAO messageQueueDAO;

    @Override
    public void addToMsgQueue(Message msg){
        MessageEntity msgEntity = new MessageEntity();
        msgEntity.setAttemptIndex(0);
        msgEntity.setMsg(msg.getContent());
        String loginId = POIChangeService.getPOIUpdateUser(msg);
        if(LionConfigUtils.getProperty("rotate-shop-biz.poi.batch.update.user","-15900").equals(loginId)){
            msgEntity.setSource(MessageSource.SYSTEM);
        }else{
            msgEntity.setSource(MessageSource.PERSON);
        }
        msgEntity.setStatus(MessageStatus.NEW);
        msgEntity.setSwallowId(msg.getMessageId());
        msgEntity.setType(POIMessageType.SHOP_UPDATE);
        messageQueueDAO.addToMessageQueue(msgEntity);
    }
}
