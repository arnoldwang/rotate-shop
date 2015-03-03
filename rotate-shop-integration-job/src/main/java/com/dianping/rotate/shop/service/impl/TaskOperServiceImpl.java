package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.service.TaskOperService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luoming on 15/3/3.
 */
@Service("taskOperService")
public class TaskOperServiceImpl implements TaskOperService {

    @Autowired
    private Scheduler scheduler;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean pause(String taskName) {
        try {
            scheduler.pauseJob(taskName + "Name", taskName + "Group");
            return true;
        } catch (SchedulerException e) {
            logger.error("pauseJob " + taskName + " exception.", e);
        }
        return false;
    }

    @Override
    public boolean resume(String taskName) {
        try {
            scheduler.resumeJob(taskName + "Name", taskName + "Group");
            return true;
        } catch (SchedulerException e) {
            logger.error("pauseJob " + taskName + " exception.", e);
        }
        return false;
    }

    @Override
    public boolean trigger(String taskName) {
        try {
            scheduler.triggerJob(taskName + "Name", taskName + "Group");
            return true;
        } catch (SchedulerException e) {
            logger.error("pauseJob " + taskName + " exception.", e);
        }
        return false;
    }

}
