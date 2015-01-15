package com.dianping.rotate.shop.json;

import java.util.List;
import java.util.Map;

/**
 * Created by zaza on 15/1/15.
 */

/*
Topic:dp_apollo_rotategroup_change
消息体格式：
{
    "type":"merge",
    "oldValues": [{"rotateGroupId":111111},{"rotateGroupId":333333}],
    "newValues":[{"rotateGroupId":111111}]
}
*/
public class RotateGroupMessage {
    public RotateGroupMessage(){}
    public RotateGroupMessage(String type,List<Map<String,Object>> oldValues,
                              List<Map<String,Object>> newValues){
        this.type = type;
        this.oldValues = oldValues;
        this.newValues = newValues;
    }
    private String type;
    private List<Map<String,Object>> oldValues;
    private List<Map<String,Object>> newValues;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Map<String, Object>> getOldValues() {
        return oldValues;
    }

    public void setOldValues(List<Map<String, Object>> oldValues) {
        this.oldValues = oldValues;
    }

    public List<Map<String, Object>> getNewValues() {
        return newValues;
    }

    public void setNewValues(List<Map<String, Object>> newValues) {
        this.newValues = newValues;
    }
}
