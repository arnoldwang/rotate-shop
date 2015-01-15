package com.dianping.rotate.shop.service.impl.message.producer;

import com.dianping.pigeon.remoting.provider.config.annotation.Service;
import com.dianping.rotate.shop.utils.JsonUtil;

import java.io.IOException;

/**
 * Created by zaza on 15/1/15.
 */
@Service
public class RotateGroupShopMessageProducer extends AbstractMessageProducer {
    @Override
    public String getMessageJson(Object msg) throws IOException {
        return JsonUtil.toStr(msg);
    }
}
