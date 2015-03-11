package com.dianping.rotate.shop.service;

import java.util.List;

/**
 * Created by yangjie on 1/13/15.
 */
public interface ShopService {

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
	 * 关闭门店
	 * @param shopId
	 */
    public void closeShop(int shopId);

	/**
	 * 重开门店
	 * @param shopId
	 */
    public void openShop(int shopId);

	/**
	 * 更新所有shop id对应的轮转组的状态，包括是否单店
	 * 门店状态逻辑同updateRotateGroupTypeAndStatusByRotateGroupId
	 * @param shopId
	 */
	public void updateRotateGroupTypeByShopID(int shopId);

	/**
	 * 前台操作调用此方法
	 * 更新轮转组的状态，包括是否单店，是否有效
	 * 逻辑是当轮转组有
	 * 1个门店 -> 单店
	 * 2个及以上门店 -> 连锁店
	 * @param rotateGroupID
	 */
	public void updateRotateGroupTypeByRotateGroupId(int rotateGroupID);

	/**
	 * 优化后，供Job使用
	 * 更新轮转组的状态，包括是否单店，是否有效
	 * 逻辑是当轮转组有
	 * 1个门店 -> 单店
	 * 2个及以上门店 -> 连锁店
	 * @param rotateGroupID
	 */
	public void updateRotateGroupType(int rotateGroupID);

	/**
	 * 分页批量获取rotateGroupID
	 * @param pageSize 每页的数量
	 * @param offset 偏移量
	 * @return ID列表
	 */
	public List<Integer> getRotateGroupIDList(int pageSize, int offset);

	/**
	 * 获得最大的RotateGroup的数量
	 * @return
	 */
	public int getRotateGroupNum();

	/**
	 * 更新此轮转组下ApolloShopExtend的Type
	 * @param rotateGroupID
	 */
	public void updateShopExtendTypeByRotateGroupID(int rotateGroupID);
}
