package com.dianping.roate.shop.service;
import com.dianping.rotate.shop.utils.JsonUtil;
import com.dianping.swallow.common.message.Message;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zaza on 15/1/5.
 */
public class POIChangeService {
    public static int getMessageType(Message msg){
        try{
            Map<String,Object> msgMaps = JsonUtil.fromStrToMap(msg.getContent());
            if(msgMaps.containsKey("messageType")){
                return Integer.parseInt(msgMaps.get("messageType").toString());
            }
        }catch(Exception ex){}
        return 0;
    }

    public static String getPOIUpdateUser(Message msg){
        try{
            Map<String,Object> msgMaps = JsonUtil.fromStrToMap(msg.getContent());
            if(msgMaps.containsKey("lastModifyUser")){
                return msgMaps.get("lastModifyUser").toString();
            }
        }catch(Exception ex){}
        return "0";
    }
}
