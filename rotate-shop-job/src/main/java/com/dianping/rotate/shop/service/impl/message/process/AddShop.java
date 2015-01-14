package com.dianping.rotate.shop.service.impl.message.process;

import com.dianping.rotate.shop.constants.ActionType;
import com.dianping.rotate.shop.constants.BizType;
import com.dianping.rotate.shop.entity.MessageEntity;
import org.springframework.stereotype.Service;

/**
 * Created by yangjie on 1/14/15.
 */
@Service
public class AddShop extends Abstract {
    @Override
    public void process(int type, MessageEntity msg) {
        try{
            shopService.addPoiByUser(msg.getMsg());
            shopMessageProducer.send(123, BizType.TUAN_HUI_6, ActionType.INSERT);
            messageDAO.deleteMessage(msg.getId());
        }catch(Exception ex){
            messageDAO.updateMessageAttemptIndex(msg.getId(),msg.getAttemptIndex()+1);
            logger.error(ex.getMessage(),ex);
        }
    }
}
