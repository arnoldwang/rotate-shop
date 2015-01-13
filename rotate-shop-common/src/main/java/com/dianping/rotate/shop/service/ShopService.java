package com.dianping.rotate.shop.service;

/**
 * Created by yangjie on 1/13/15.
 */
public interface ShopService {
    public void mergeShops(int shopId, int toShopId);
    public void restoreMergedShops(int shopId, int restoreShopId);
}
