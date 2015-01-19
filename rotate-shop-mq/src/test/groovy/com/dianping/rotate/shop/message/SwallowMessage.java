package com.dianping.rotate.shop.message;

/**
 * Created by zaza on 15/1/19.
 */
public class SwallowMessage extends AbstractMessage {
    private String msg;
    private long msgId;

    public String getContent(){
        return this.msg;
    }

    public void setContent(String msg){
        this.msg = msg;
    }

    public String getProperty(String l){
        return null;
    }

    public long getMessageId(){
        return this.msgId;
    }

    public void setMessageId(long msgId){
        this.msgId = msgId;
    }


}
