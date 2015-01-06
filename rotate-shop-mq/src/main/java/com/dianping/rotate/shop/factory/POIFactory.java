package com.dianping.rotate.shop.factory;
import com.dianping.rotate.shop.constants.POIMessageType;

/**
 * Created by zaza on 15/1/5.
 */
public class POIFactory {

    public static POIChange factory(int type){
        if(type == POIMessageType.SHOP_ADD_BATCH){
            return new POIAddBatch();
        }
        if(type == POIMessageType.SHOP_CATEGORY){
            return new POICategory();
        }
        if(type == POIMessageType.SHOP_MERGE){
            return new POIMerge();
        }
        if(type == POIMessageType.SHOP_MERGE_RECOVER){
            return new POIMergeRecover();
        }
        if(type == POIMessageType.SHOP_REGION){
            return new POIRegion();
        }
        if(type == POIMessageType.SHOP_UPDATE){
            return new POIUpdate();
        }
        if(type == POIMessageType.SHOP_ADD){
            return new POIAdd();
        }
        return null;
    }
}
