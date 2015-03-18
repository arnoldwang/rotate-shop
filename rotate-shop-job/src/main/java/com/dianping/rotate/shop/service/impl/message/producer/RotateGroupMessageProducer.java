package com.dianping.rotate.shop.service.impl.message.producer;

import com.dianping.rotate.shop.json.RotateGroupMessage;
import com.dianping.rotate.shop.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by zaza on 15/1/14.
 */
@Service("rotateGroupMessageProducerJob")
public class RotateGroupMessageProducer extends AbstractMessageProducer {
    @Override
    public String getMessageJson(Object msg) throws IOException{
        return JsonUtil.toStr(msg);
    }
}
