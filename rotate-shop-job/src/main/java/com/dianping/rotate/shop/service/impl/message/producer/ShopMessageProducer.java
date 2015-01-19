package com.dianping.rotate.shop.service.impl.message.producer;

import com.dianping.rotate.shop.json.ShopMessage;
import com.dianping.rotate.shop.service.MessageProducer;
import com.dianping.rotate.shop.utils.JsonUtil;
import com.dianping.swallow.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by zaza on 15/1/14.
 */
@Service
public class ShopMessageProducer extends AbstractMessageProducer {
    @Override
    public String getMessageJson(Object msg) throws IOException{
        return JsonUtil.toStr(msg);
    }
}
