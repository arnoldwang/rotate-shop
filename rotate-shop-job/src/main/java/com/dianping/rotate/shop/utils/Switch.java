package com.dianping.rotate.shop.utils;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.rotate.shop.constants.LionKey;

/**
 * Created by zaza on 15/1/12.
 */
public class Switch {
    public static boolean on(){
        return LionConfigUtils.getProperty(LionKey.JOB_SWITCH, "on").equals("on");
    }

    public static boolean off(){
        return LionConfigUtils.getProperty(LionKey.JOB_SWITCH, "off").equals("off");
    }
}
