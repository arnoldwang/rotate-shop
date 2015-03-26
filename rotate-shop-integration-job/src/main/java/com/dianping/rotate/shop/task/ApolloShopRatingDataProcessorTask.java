package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.constants.ApolloShopBusinessTypeEnum;
import com.dianping.rotate.shop.constants.BizTypeEnum;
import com.dianping.rotate.shop.json.ApolloShopBusinessStatusEntity;
import com.dianping.rotate.shop.json.ApolloShopExtendEntity;
import com.dianping.rotate.shop.service.ShopBusinessStatusService;
import com.dianping.rotate.shop.service.ShopExtendService;
import com.dianping.rotate.shop.utils.Beans;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luoming on 15/1/21.
 */
public class ApolloShopRatingDataProcessorTask {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final static String REPORT_NAME = "dpdm_tg_shop_rating_star_information";

    public void process() {
        try {
            long start = System.currentTimeMillis();
            logger.info("ApolloShopRatingDataProcessorTask start");

            ExecutorService exec = Executors.newFixedThreadPool(10);
            final BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<Future<Integer>>(15);
            final CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec, queue);

            ReportDataProcessor reportDataProcessor = new ReportDataProcessor();
            Beans.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(reportDataProcessor);
            reportDataProcessor.setReportName(REPORT_NAME);
            reportDataProcessor.setOrderByName("shop_id");
            while(!reportDataProcessor.isDataOver()) {
                logger.info(reportDataProcessor.getReportName() + " " + reportDataProcessor.getPageIndex());
                Callable<Integer> apolloShopBusinessDataProcessorTaskThread =
                        new ApolloShopBusinessDataProcessorTaskThread(reportDataProcessor.getData());
                Beans.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(apolloShopBusinessDataProcessorTaskThread);
                completionService.submit(apolloShopBusinessDataProcessorTaskThread);
                sleep();
            }
            exec.shutdown();
            logger.info("ApolloShopRatingDataProcessorTask end("+(System.currentTimeMillis()-start)+"ms)");
        } catch(Exception e) {
            logger.error("ApolloShopRatingDataProcessorTask fail", e);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
