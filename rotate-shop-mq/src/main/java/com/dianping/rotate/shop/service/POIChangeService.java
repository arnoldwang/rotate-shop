package com.dianping.rotate.shop.service;
import com.dianping.rotate.shop.utils.JsonUtil;
import com.dianping.swallow.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zaza on 15/1/5.
 */
public class POIChangeService {
    public final static String POI_CHANGE_MESSAGE_TYPE_FIELD="messageType";
    public final static String POI_CHANGE_MESSAGE_USER_FIELD="lastModifyUser";
    public final static String DP_ACTION_MESSAGE_TYPE_FIELD ="type";
    /*
    * dp_poi_change这个Topic会有各种不同的MessageType
    */
    public static int getPOIChangeMessageType(Message msg){
        try{
            Map<String,Object> msgMaps = JsonUtil.fromStrToMap(msg.getContent());
            if(msgMaps.containsKey(POI_CHANGE_MESSAGE_TYPE_FIELD)){
                return Integer.parseInt(msgMaps.get(POI_CHANGE_MESSAGE_TYPE_FIELD).toString());
            }
        }catch(Exception ex){}
        return -1;
    }

    public static String getPOIUpdateUser(Message msg){
        try{
            Map<String,Object> msgMaps = JsonUtil.fromStrToMap(msg.getContent());
            if(msgMaps.containsKey(POI_CHANGE_MESSAGE_USER_FIELD)){
                return msgMaps.get(POI_CHANGE_MESSAGE_USER_FIELD).toString();
            }
        }catch(Exception ex){}
        return "0";
    }
    /*
    * dp_action这个Topic会有不同的type
    */
    public static int getDPActionMessageType(Message msg){
        try{
            Map<String,Object> msgMaps = JsonUtil.fromStrToMap(msg.getContent());
            if(msgMaps.containsKey(DP_ACTION_MESSAGE_TYPE_FIELD)){
                return Integer.parseInt(msgMaps.get(DP_ACTION_MESSAGE_TYPE_FIELD).toString());
            }
        }catch(Exception ex){}
        return -1;
    }
}
