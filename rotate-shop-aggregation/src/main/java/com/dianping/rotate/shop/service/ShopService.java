package com.dianping.rotate.shop.service;

import java.util.List;

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
	 * 优化后
	 * 更新轮转组的状态，包括是否单店，是否有效
	 * 逻辑是当轮转组有
	 * 0个门店 -> 无效
	 * 1个门店 -> 有效 单店
	 * 2个及以上门店 -> 有效 连锁店
	 * @param rotateGroupID
	 */
	public void updateRotateGroupTypeAndStatus(int rotateGroupID);

	/**
	 * poi新增门店后，客户轮转系统初始化shop、shopExtend、region、category、rotateGroup、rotateGroupShop
	 * @param shopId
	 */
	public void addShop(int shopId);

	/**
	 * poi更新门店信息后，客户轮转系统更新shop、region、category、rotateGroup、rotateGroupShop
	 * 更新poi信息
	 * @param shopId
	 */
	public void updateShop(int shopId);

	/**
	 * 分页批量获取rotateGroupID
	 * @param pageSize 每页的数量
	 * @param offset 偏移量
	 * @return ID列表
	 */
	public List<Integer> getRotateGroupIDList(int pageSize, int offset);

	/**
	 * 获得最大的RotateGroupID
	 * @return
	 */
	public int getMaxRotateGroupID();

	/**
	 * 判断此轮转组下所否有门店为大客户
	 * @param rotateGroupID
	 * @return 有返回true，没有返回false
	 */
	public boolean isHaveVipByRotateGroupID(int rotateGroupID);
}
