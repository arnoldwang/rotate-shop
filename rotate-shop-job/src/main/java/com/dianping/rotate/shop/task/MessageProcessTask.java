package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.service.MessageProcessor;
import com.dianping.rotate.shop.service.impl.*;
import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zaza on 15/1/8.
 */
public class MessageProcessTask {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final ExecutorService threadPool = Executors.newFixedThreadPool(7);
    @Autowired
    private POIAddMessageProcessor addMessageProcessor;
    @Autowired
    private POICategoryMessageProcessor categoryMessageProcessor;
    @Autowired
    private POIMergeMessageProcessor mergeMessageProcessor;
    @Autowired
    private POIMergeRecoverMessageProcessor mergeRecoverMessageProcessor;
    @Autowired
    private POIRegionMessageProcessor regionMessageProcessor;
    @Autowired
    private POIStatusMessageProcessor statusMessageProcessor;
    @Autowired
    private POIUpdateMessageProcessor updateMessageProcessor;

    public void run(){
        try{
            process();
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }


    protected void process(){
        try{
            threadPool.execute(new POIAddRunnable());
            threadPool.execute(new POICategoryRunnable());
            threadPool.execute(new POIMergeRunnable());
            threadPool.execute(new POIMergeRecoverRunnable());
            threadPool.execute(new POIStatusRunnable());
            threadPool.execute(new POIRegionRunnable());
            threadPool.execute(new POIUpdateRunnable());
        }catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }

    class POIAddRunnable implements Runnable{
        public void run(){
            addMessageProcessor.process();
        }
    }

    class POICategoryRunnable implements Runnable{
        public void run(){
            categoryMessageProcessor.process();
        }
    }

    class POIMergeRunnable implements Runnable{
        public void run(){
            mergeMessageProcessor.process();
        }
    }

    class POIMergeRecoverRunnable implements Runnable{
        public void run(){
            mergeRecoverMessageProcessor.process();
        }
    }

    class POIStatusRunnable implements Runnable{
        public void run(){
            statusMessageProcessor.process();
        }
    }

    class POIRegionRunnable implements Runnable{
        public void run(){
            regionMessageProcessor.process();
        }
    }

    class POIUpdateRunnable implements Runnable{
        public void run(){
            updateMessageProcessor.process();
        }
    }
}
