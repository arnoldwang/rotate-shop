package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.constants.ApolloShopBusinessTypeEnum;
import com.dianping.rotate.shop.dao.ApolloShopBusinessStatusDAO;
import com.dianping.rotate.shop.json.ApolloShopBusinessStatusEntity;
import com.dianping.rotate.shop.service.ShopBusinessStatusService;
import com.dianping.trade.data.api.ReportRemoteService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luoming on 15/1/21.
 */
public class ApolloShopBusinessDataProcessorTask {

    @Autowired
    private ShopBusinessStatusService shopBusinessStatusService;

    @Autowired
    private ReportDataProcessor reportDataProcessor;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final static String REPORT_NAME = "dprpt_dp_shop_coop_status_1";

    public void process() {
        try {
            long start = System.currentTimeMillis();
            logger.info("ApolloShopBusinessDataProcessorTask start");
            deleteData();
            reportDataProcessor.setReportName(REPORT_NAME);
            while(!reportDataProcessor.isDataOver()) {
                processData(reportDataProcessor.getData());
                sleep();
            }
            clearData();
            logger.info("ApolloShopBusinessDataProcessorTask end("+(System.currentTimeMillis()-start)+"ms)");
        } catch(Exception e) {
            logger.error("ApolloShopBusinessDataProcessorTask fail", e);
        }
    }

    private void processData(List<HashMap<String, Object>> data) {
        if(CollectionUtils.isNotEmpty(data)) {
            List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList = new ArrayList<ApolloShopBusinessStatusEntity>();
            for(int i=0;i<data.size();i++) {
                ApolloShopBusinessStatusEntity apolloShopBusinessStatusEntity = trans(data.get(i));
                if(apolloShopBusinessStatusEntity != null) {
                    apolloShopBusinessStatusEntityList.add(apolloShopBusinessStatusEntity);
                }
            }
            if(CollectionUtils.isNotEmpty(apolloShopBusinessStatusEntityList)) {
                add(apolloShopBusinessStatusEntityList);
            }
        }
    }

    private void deleteData() {
        shopBusinessStatusService.deleteApolloShopBusinessStatusAll();
    }

    private void clearData() {
        shopBusinessStatusService.clearApolloShopBusinessStatus();
    }

    private void add(List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList) {
        shopBusinessStatusService.addApolloShopBusinessStatus(apolloShopBusinessStatusEntityList);
    }

    private ApolloShopBusinessStatusEntity trans(Map<String, Object> map) {
        ApolloShopBusinessStatusEntity apolloShopBusinessStatusEntity = null;
        Object shopID = map.get("shop_id");
        Object coopStatus = map.get("coop_status");
        Object coopType = map.get("coop_type");
        Object lastEndTs = map.get("last_end_ts");
        if(shopID != null && coopStatus != null && coopType != null) {
            apolloShopBusinessStatusEntity = new ApolloShopBusinessStatusEntity();
            apolloShopBusinessStatusEntity.setShopID((Integer) shopID);
            apolloShopBusinessStatusEntity.setCooperationStatus((Integer) coopStatus);
            apolloShopBusinessStatusEntity.setBusinessType(transCoopType((Integer) coopType));
            apolloShopBusinessStatusEntity.setOfflineDate(lastEndTs != null ? new Date(((Timestamp) lastEndTs).getTime()) : null);
        }
        return apolloShopBusinessStatusEntity;
    }

    private Integer transCoopType(Integer coopType) {
        if(coopType == 1) {
            return ApolloShopBusinessTypeEnum.TUAN_GOU.getCode();
        } else if(coopType == 2) {
            return ApolloShopBusinessTypeEnum.HUI_YUAN_KA.getCode();
        } else if(coopType == 3) {
            return ApolloShopBusinessTypeEnum.YU_DING.getCode();
        } else if(coopType == 4) {
            return ApolloShopBusinessTypeEnum.TUI_GUANG.getCode();
        }
        return ApolloShopBusinessTypeEnum.WEI_ZHI.getCode();
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
