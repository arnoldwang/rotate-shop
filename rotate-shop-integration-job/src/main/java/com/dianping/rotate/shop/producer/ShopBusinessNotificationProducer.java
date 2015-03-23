package com.dianping.rotate.shop.producer;

import java.io.IOException;

/**
 * Created by zaza on 15/3/6.
 */
@org.springframework.stereotype.Service("shopBusinessNotificationProducer")
public class ShopBusinessNotificationProducer extends AbstractMessageJobProducer {
    @Override
    public String getMessageJson() throws IOException {
        return "{\"messageType\":1}";
    }
}
