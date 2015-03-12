package com.dianping.rotate.shop.producer;

import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * Created by zaza on 15/3/6.
 */
@org.springframework.stereotype.Service("shopBusinessNotificationProducer")
public class ShopBusinessNotificationProducer extends AbstractMessageProducer{
    @Override
    public String getMessageJson() throws IOException {
        return "{\"messageType\":1}";
    }
}
