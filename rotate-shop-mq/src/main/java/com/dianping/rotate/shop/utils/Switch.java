package com.dianping.rotate.shop.utils;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.rotate.shop.constants.LionKey;

/**
 * Created by zaza on 15/1/12.
 */
public class Switch {
    //打开，接受消息
    public static boolean on(){
        return LionConfigUtils.getProperty(LionKey.MQ_SWITCH, "on").equals("on");
    }
    //关闭，接受消息，但是不处理消息
    public static boolean off(){
        return LionConfigUtils.getProperty(LionKey.MQ_SWITCH, "off").equals("off");
    }
    //关闭，不接受消息
    public static boolean exception(){
        return LionConfigUtils.getProperty(LionKey.MQ_SWITCH, "exception").equals("exception");
    }
}
