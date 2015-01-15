package com.dianping.rotate.shop.service;

/**
 * Created by yangjie on 1/13/15.
 */
public interface ShopService {
    public void closeShop(int shopId);
    public void openShop(int shopId);
	public void updateRotateGroupTypeAndStatusByShopID(int shopId);
	public void updateRotateGroupTypeAndStatusByRotateGroupId(int rotateGroupID);
	public void addShop(int shopId);

	/**
	 * 更新poi信息
	 * @param msg 消息体
	 */
	public void updatePoi(String msg);
}
