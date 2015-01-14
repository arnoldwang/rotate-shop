package com.dianping.rotate.shop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaoqiang.zhu on 2014/6/30.
 */
public class CommonUtil {

    private static final String DATETIME_FMT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FMT = "yyyy-MM-dd";

    SimpleDateFormat sdfDate = new SimpleDateFormat(DATE_FMT);
    SimpleDateFormat sdfDatetime = new SimpleDateFormat(DATETIME_FMT);

    public Date stringToDateTime(String dateStr){
        Date result = null;
        if ((dateStr != null) && !dateStr.trim().isEmpty()) {
            try {
                result = sdfDatetime.parse(dateStr.trim());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public String datetimeToString(Date date){
        String dateStr = null;
        if(date != null){
            dateStr = sdfDatetime.format(date);
        }
        return dateStr;
    }

    public Date stringToDate(String dateStr){
        Date result = null;
        if ((dateStr != null) && !dateStr.trim().isEmpty()) {
            try {
                result = sdfDate.parse(dateStr.trim());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public String dateToString(Date date){
        String dateStr = null;
        if(date != null){
            dateStr = sdfDate.format(date);
        }
        return dateStr;
    }

}
