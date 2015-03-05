package com.dianping.rotate.shop.service;

/**
 * Created by luoming on 15/3/3.
 */
public interface TaskOperService {

    public boolean pause(String taskName);

    public boolean resume(String taskName);

    public boolean trigger(String taskName);

}
