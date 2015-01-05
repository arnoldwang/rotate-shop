package com.dianping.roate.shop.exception;

/**
 * Created by zaza on 15/1/5.
 */
public class POIMessageException extends RuntimeException{
    public POIMessageException(){}

    public POIMessageException(String message){
        super(message);
    }

    public POIMessageException(String message,Throwable cause){
        super(message,cause);
    }

    public POIMessageException(Throwable cause){
        super(cause);
    }
}
