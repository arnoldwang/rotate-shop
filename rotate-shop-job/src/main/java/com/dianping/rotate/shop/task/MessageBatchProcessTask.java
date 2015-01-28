package com.dianping.rotate.shop.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zaza on 15/1/8.
 */
public class MessageBatchProcessTask {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public void run(){
		try{
			for (Runnable runner: runners) {
				threadPool.execute(runner);
			}
		}catch(Exception ex){
			logger.error(ex.getMessage(), ex);
		}
    }

    private List<Runnable> runners;

    public void setRunners(List<Runnable> runners) {
        this.runners = runners;
    }

}
