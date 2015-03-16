package com.dianping.rotate.shop.producer;


import com.beust.jcommander.internal.Lists;
import com.dianping.rotate.shop.json.RotateGroupOwnerJson;
import com.dianping.rotate.shop.json.RotateGroupShopJson;
import com.dianping.rotate.shop.json.ShopJson;
import com.dianping.rotate.shop.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zaza on 15/3/11.
 */
@Service("rotateGroupShopMessageProducer")

public class RotateGroupShopMessageProducer extends AbstractMessageProducer{
    public void send(String msg){
        try{
            rotateGroupShopProducerClient.sendMessage(msg);
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }

    public  void send(Integer newRotateGroupId,Integer newOwner,List<Integer> shops,
                     Integer oldRotateGroupId,Integer oldOwner){
        try{
            RotateGroupShopJson rotateGroupShopJson = new RotateGroupShopJson();
            rotateGroupShopJson.setType("insert/delete");
            rotateGroupShopJson.setRotateGroup(new RotateGroupOwnerJson(newRotateGroupId, newOwner));
            rotateGroupShopJson.setOldRotateGroup(new RotateGroupOwnerJson(oldRotateGroupId,oldOwner));
            List<ShopJson> shopJsonList = Lists.newArrayList();
            for(Integer shopId:shops){
                shopJsonList.add(new ShopJson(shopId));
            }
            rotateGroupShopProducerClient.sendMessage(JsonUtil.toStr(rotateGroupShopJson));
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }

}
