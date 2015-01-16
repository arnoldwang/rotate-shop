package com.dianping.rotate.shop.service;

/**
 * Created by yangjie on 1/13/15.
 */
public interface ShopService {
    public void closeShop(int shopId);
    public void openShop(int shopId);

	/**
	 * 更新所有shop id对应的轮转组的状态，包括是否单店，是否有效
	 * 门店状态逻辑同updateRotateGroupTypeAndStatusByRotateGroupId
	 * @param shopId
	 */
	public void updateRotateGroupTypeAndStatusByShopID(int shopId);

	/**
	 * 更新轮转组的状态，包括是否单店，是否有效
	 * 逻辑是当轮转组有
	 * 0个门店 -> 无效
	 * 1个门店 -> 有效 单店
	 * 2个及以上门店 -> 有效 连锁店
	 * @param rotateGroupID
	 */
	public void updateRotateGroupTypeAndStatusByRotateGroupId(int rotateGroupID);

	/**
	 * poi新增门店后，客户轮转系统初始化shop、shopExtend、region、category、rotateGroup、rotateGroupShop
	 * @param shopId
	 */
	public void addShop(int shopId);

	/**
	 * poi更新门店信息后，客户轮转系统更新shop、shopExtend、region、category、rotateGroup、rotateGroupShop
	 * @param shopId
	 */
	public void updateShop(int shopId);
}
