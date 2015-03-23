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
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luoming on 15/1/21.
 */
public class ApolloShopRatingDataProcessorTask {

    @Autowired
    private ShopExtendService shopExtendService;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final static String REPORT_NAME = "dpdm_tg_shop_rating_star_information";

    public void process() {
        try {
            long start = System.currentTimeMillis();
            logger.info("ApolloShopRatingDataProcessorTask start");
            ThreadPoolManager threadPoolManager = new ThreadPoolManager();
            ReportDataProcessor reportDataProcessor = new ReportDataProcessor();
            Beans.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(reportDataProcessor);
            reportDataProcessor.setReportName(REPORT_NAME);
            reportDataProcessor.setOrderByName("shop_id");
            while(!reportDataProcessor.isDataOver()) {
                ApolloShopBusinessDataProcessorTaskThread apolloShopBusinessDataProcessorTaskThread =
                        new ApolloShopBusinessDataProcessorTaskThread(reportDataProcessor.getData());
                threadPoolManager.execute(apolloShopBusinessDataProcessorTaskThread);
                sleep();
            }
            threadPoolManager.shutdown();
            logger.info("ApolloShopRatingDataProcessorTask end("+(System.currentTimeMillis()-start)+"ms)");
        } catch(Exception e) {
            logger.error("ApolloShopRatingDataProcessorTask fail", e);
        }
    }

    private void processData(List<HashMap<String, Object>> data) {
        if(CollectionUtils.isNotEmpty(data)) {
            List<ApolloShopExtendEntity> apolloShopExtendEntityList = new ArrayList<ApolloShopExtendEntity>();
            for(int i=0;i<data.size();i++) {
                ApolloShopExtendEntity apolloShopExtendEntity = trans(data.get(i));
                if(apolloShopExtendEntity != null) {
                    apolloShopExtendEntityList.add(apolloShopExtendEntity);
                }
            }
            if(CollectionUtils.isNotEmpty(apolloShopExtendEntityList)) {
                update(apolloShopExtendEntityList);
            }
        }
    }

    private void update(List<ApolloShopExtendEntity> apolloShopExtendEntityList) {
        shopExtendService.updateApolloShopExtendRating(apolloShopExtendEntityList);
    }

    private ApolloShopExtendEntity trans(Map<String, Object> map) {
        ApolloShopExtendEntity apolloShopExtendEntity = null;
        Object shopID = map.get("shop_id");
        Object rating = map.get("rating");
        if(shopID != null) {
            apolloShopExtendEntity = new ApolloShopExtendEntity();
            apolloShopExtendEntity.setShopID((Integer) shopID);
            // 源表只有交易平台的数据
            apolloShopExtendEntity.setBizID(BizTypeEnum.JYPT.getCode());
            apolloShopExtendEntity.setRating(rating != null ? (String) rating : "");
        }
        return apolloShopExtendEntity;
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public class ApolloShopBusinessDataProcessorTaskThread implements Callable<Integer> {

        List<HashMap<String, Object>> data;

        public ApolloShopBusinessDataProcessorTaskThread(List<HashMap<String, Object>> data) {
            this.data = data;
        }

        @Override
        public Integer call() throws Exception {
            processData(data);
            return null;
        }
    }

}
