package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.swallow.common.message.Destination
import com.dianping.swallow.producer.Producer
import com.dianping.swallow.producer.ProducerConfig
import com.dianping.swallow.producer.ProducerMode
import com.dianping.swallow.producer.impl.ProducerFactoryImpl
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: zhenwei.wang
 * Date: 15-2-13
 */
class SwallowServiceTest extends AbstractSpockTest {

    @Autowired
    SwallowService swallowService;

    def "test send 500000 msg"(){
        setup:
        int msgNum = 500000
        String topic = "POI变更"
        String[] shopIDs = new String[msgNum]

        when:
        for (int i = 0; i < msgNum; i++){
            shopIDs[i] = String.valueOf(i);
        }

        Map<String, Object> msgMap = swallowService.createMsg(topic, shopIDs)
        ProducerConfig config = new ProducerConfig();
        config.setMode(ProducerMode.SYNC_MODE);
        Producer p = ProducerFactoryImpl.getInstance().createProducer(Destination.topic((String) msgMap.get("topic")), config);
        List<String> swallowMsgs = (ArrayList<String>) msgMap.get("swallowMsgs");
        for (String swallowMsg : swallowMsgs) {
            p.sendMessage(swallowMsg);
        }

        then:
        500000 == swallowMsgs.size()
    }
}
