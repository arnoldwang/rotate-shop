package com.dianping.rotate.shop.message;

import com.dianping.swallow.Message;

import java.util.Date;
import java.util.Set;

/**
 * Created by zaza on 15/1/19.
 */
public abstract class AbstractMessage implements Message {
    @Override
    public Date getAddtime() {
        return null;
    }

    @Override
    public abstract Object getContent();

    @Override
    public void setProperty(String s, String s2) {

    }

    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public boolean isBackout() {
        return false;
    }

    @Override
    public int getBackoutCnt() {
        return 0;
    }

    @Override
    public String getBackoutReason() {
        return null;
    }

    @Override
    public Date getBackoutOriginalAddtime() {
        return null;
    }

    @Override
    public String getBackoutStackTrace() {
        return null;
    }
}
