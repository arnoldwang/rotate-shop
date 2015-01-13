package com.dianping.rotate.shop.service;

/**
 * Created by yangjie on 1/13/15.
 */
public interface ShopService {
    public void mergeShops(int shopId, int toShopId);

    public void restoreMergedShops(int shopId, int restoreShopId);

	/**
	 * 系统新增poi
	 * @param msg
	 */
	public void addPoiBySys(String msg);


	/**
	 * 用户新增poi
	 * @param msg
	 */
	public void addPoiByUser(String msg);

	/**
	 * 更新poi信息
	 * @param msg
	 */
	public void updatePoi(String msg);
}