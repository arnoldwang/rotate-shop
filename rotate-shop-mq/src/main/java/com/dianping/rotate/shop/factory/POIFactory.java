package com.dianping.rotate.shop.factory;
import com.dianping.rotate.shop.constants.POIMessageType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/1/5.
 */
public class POIFactory {
    @Autowired
    POIAddBatch poiAddBatch;
    @Autowired
    POICategory poiCategory;
    @Autowired
    POIMerge poiMerge;
    @Autowired
    POIMergeRecover poiMergeRecover;
    @Autowired
    POIRegion poiRegion;
    @Autowired
    POIUpdate poiUpdate;
    @Autowired
    POIAdd poiAdd;
    @Autowired
    POIStatus poiStatus;

    public  POIChange factory(int type){
        if(type == POIMessageType.SHOP_ADD_BATCH){
            return poiAddBatch;
        }
        if(type == POIMessageType.SHOP_CATEGORY){
            return poiCategory;
        }
        if(type == POIMessageType.SHOP_MERGE){
            return poiMerge;
        }
        if(type == POIMessageType.SHOP_MERGE_RECOVER){
            return poiMergeRecover;
        }
        if(type == POIMessageType.SHOP_REGION){
            return poiRegion;
        }
        if(type == POIMessageType.SHOP_UPDATE){
            return poiUpdate;
        }
        if(type == POIMessageType.SHOP_ADD){
            return poiAdd;
        }
        if(type == POIMessageType.SHOP_STATUS){
            return poiStatus;
        }
        return null;
    }
}
