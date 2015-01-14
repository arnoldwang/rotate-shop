package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.entity.ShopMessage;
import com.dianping.rotate.shop.utils.JsonUtil;

import java.io.IOException;

/**
 * Created by zaza on 15/1/14.
 */
public class MessageProducerService {
    public String getShopMessageJson(int shopId,int bizType,String action) throws IOException{
        ShopMessage shop = new ShopMessage();
        shop.setShopId(shopId);
        shop.setBizType(bizType);
        shop.setType(action);
        return JsonUtil.toStr(shop);
    }
}
