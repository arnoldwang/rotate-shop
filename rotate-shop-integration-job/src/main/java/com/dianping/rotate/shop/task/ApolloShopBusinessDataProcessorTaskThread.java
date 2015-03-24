package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.constants.BizTypeEnum;
import com.dianping.rotate.shop.json.ApolloShopExtendEntity;
import com.dianping.rotate.shop.service.ShopExtendService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by luoming on 15/3/24.
 */
public class ApolloShopBusinessDataProcessorTaskThread implements Callable<Integer> {

    @Autowired
    private ShopExtendService shopExtendService;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    List<HashMap<String, Object>> data;

    public ApolloShopBusinessDataProcessorTaskThread(List<HashMap<String, Object>> data) {
        this.data = data;
    }

    @Override
    public Integer call() throws Exception {
        processData(data);
        return null;
    }

    private void processData(List<HashMap<String, Object>> data) {
        if(CollectionUtils.isNotEmpty(data)) {
            List<ApolloShopExtendEntity> apolloShopExtendEntityList = new ArrayList<ApolloShopExtendEntity>();
            List<Integer> shopIDList = new ArrayList<Integer>();
            for(int i=0;i<data.size();i++) {
                ApolloShopExtendEntity apolloShopExtendEntity = trans(data.get(i));
                if(apolloShopExtendEntity != null) {
                    apolloShopExtendEntityList.add(apolloShopExtendEntity);
                }
                int shopID = apolloShopExtendEntity.getShopID();
                if(!shopIDList.contains(shopID)) {
                    shopIDList.add(shopID);
                }
            }
            if(CollectionUtils.isNotEmpty(apolloShopExtendEntityList)) {
                List<ApolloShopExtendEntity> apolloShopExtendEntityListDB = shopExtendService.queryApolloShopExtendByShopIDListAndBizID(shopIDList, BizTypeEnum.JYPT.getCode());
                List<ApolloShopExtendEntity> apolloShopExtendEntityListInsert = new ArrayList<ApolloShopExtendEntity>();
                List<ApolloShopExtendEntity> apolloShopExtendEntityListUpdate = new ArrayList<ApolloShopExtendEntity>();
                deleteUnupdateExtendData(apolloShopExtendEntityList, apolloShopExtendEntityListDB, apolloShopExtendEntityListInsert, apolloShopExtendEntityListUpdate);
                insert(apolloShopExtendEntityListInsert);
                update(apolloShopExtendEntityListUpdate);
            }
        }
    }

    /**
     * 去除未更新rating的数据
     * @param apolloShopExtendEntityList  待去除的数据
     * @param apolloShopExtendEntityListDB  本地数据库数据
     */
    private void deleteUnupdateExtendData(List<ApolloShopExtendEntity> apolloShopExtendEntityList, List<ApolloShopExtendEntity> apolloShopExtendEntityListDB, List<ApolloShopExtendEntity> apolloShopExtendEntityListInsert, List<ApolloShopExtendEntity> apolloShopExtendEntityListUpdate) {
        Map<Integer, String> apolloShopExtendEntityMap = new HashMap<Integer, String>();
        for(ApolloShopExtendEntity apolloShopExtendEntity : apolloShopExtendEntityListDB) {
            apolloShopExtendEntityMap.put(apolloShopExtendEntity.getShopID(), apolloShopExtendEntity.getRating());
        }

        for(ApolloShopExtendEntity apolloShopExtendEntity : apolloShopExtendEntityList) {
            String rating = apolloShopExtendEntityMap.get(apolloShopExtendEntity.getShopID());
            if(rating == null) {
                apolloShopExtendEntityListInsert.add(apolloShopExtendEntity);
            } else if(!rating.equals(apolloShopExtendEntity.getRating())) {
                apolloShopExtendEntityListUpdate.add(apolloShopExtendEntity);
            }
        }
    }

    private void update(List<ApolloShopExtendEntity> apolloShopExtendEntityList) {
        if(CollectionUtils.isNotEmpty(apolloShopExtendEntityList)) {
            logger.info("ApolloShopBusinessDataProcessorTaskThread update " + apolloShopExtendEntityList.size());
            shopExtendService.updateApolloShopExtendRating(apolloShopExtendEntityList);
        }
    }

    private void insert(List<ApolloShopExtendEntity> apolloShopExtendEntityList) {
        if(CollectionUtils.isNotEmpty(apolloShopExtendEntityList)) {
            logger.info("ApolloShopBusinessDataProcessorTaskThread insert " + apolloShopExtendEntityList.size());
            shopExtendService.addApolloShopExtend(apolloShopExtendEntityList);
        }
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
            apolloShopExtendEntity.setType(0);
            apolloShopExtendEntity.setStatus(1);
        }
        return apolloShopExtendEntity;
    }

}
