package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.constants.POIMessageType;
import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.rotate.shop.service.POIService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/8.
 */
public class MessageProcessService {

    @Autowired
    private MessageQueueDAO messageDAO;
    @Autowired
    private POIService poiService;

    public Integer messageProcess(MessageEntity msg,int type){
        if(type == POIMessageType.SHOP_ADD){
            poiService.addPoiByUser(msg.getMsg());
            messageDAO.deleteMessage(msg.getId());
        }
        if(type == POIMessageType.SHOP_STATUS){

        }
        if(type == POIMessageType.SHOP_ADD_BATCH){

        }
        if(type == POIMessageType.SHOP_CATEGORY){

        }
        if(type == POIMessageType.SHOP_MERGE){

        }
        if(type == POIMessageType.SHOP_MERGE_RECOVER){

        }
        if(type == POIMessageType.SHOP_UPDATE){

        }

        return 1;
    }
}
