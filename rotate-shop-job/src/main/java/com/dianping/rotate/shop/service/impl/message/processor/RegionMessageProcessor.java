package com.dianping.rotate.shop.service.impl.message.processor;

import com.dianping.pigeon.remoting.provider.config.annotation.Service;
import com.dianping.rotate.shop.json.MessageEntity;

/**
 * Created by zaza on 15/1/28.
 */
public class RegionMessageProcessor extends AbstractMessageProcessor {
    public void process(MessageEntity message) throws Exception{
        //To change body of implemented methods use File | Settings | File Templates.
        //对于商圈的变化，目前不通知下游，只在后台记下，线下处理
    }
}
