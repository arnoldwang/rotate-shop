package com.dianping.rotate.shop.service.impl.message.process;

import com.dianping.rotate.shop.entity.MessageEntity;
import com.dianping.rotate.shop.service.MessageProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zaza on 15/1/8.
 */
@Service("messageProcessService")
public class Dispatcher implements MessageProcessService {
    private Map<Integer, MessageProcessService> processors;

    public Map<Integer, MessageProcessService> getProcessors() {
        return processors;
    }

    public void setProcessors(Map<Integer, MessageProcessService> processors) {
        this.processors = processors;
    }

    @Override
    public void process(int type, MessageEntity msg){
        MessageProcessService processor = processors.get(type);
        if (processor == null) {
            throw new IllegalArgumentException("消息类型不能处理,type=" + type);
        }
        processor.process(type, msg);
    }
}
