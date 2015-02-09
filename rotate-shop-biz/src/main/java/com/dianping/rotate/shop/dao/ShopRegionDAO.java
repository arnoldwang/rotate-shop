package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.ShopRegionEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ShopRegionDAO extends GenericDao {

	/**
	 * 查找与此RegionID相关的所有ShopRegion
	 * @param regionID
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopRegionByRegionID(@DAOParam("regionID") int regionID);

	/**
	 * 查找此shopID下所有ShopRegion
	 * @param shopID
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopRegionByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 按shopID列表查找所有ShopRegion
	 * @param shopIDList
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopRegionByShopIDList(@DAOParam("shopIDList") List<Integer> shopIDList);

	/**
	 * 查找门店的主Region
	 * @param shopID
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopMainRegionByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 按shopID列表查找所有主Region
	 * @param shopIDList
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopMainRegionByShopIDList(@DAOParam("shopIDList") List<Integer> shopIDList);

	/**
	 * 按shopID和RegionID查找ShopRegion
	 * @param shopID
	 * @param regionID
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopRegionByShopIDAndRegionID(@DAOParam("shopID") int shopID, @DAOParam("regionID") int regionID);

	/**
	 * 添加ShopRegion
	 * @param shopRegion
	 * @return
	 */
    @DAOAction(action = DAOActionType.INSERT)
    int addShopRegion(@DAOParam("shopRegion") ShopRegionEntity shopRegion);

	/**
	 * 删除与此Region相关的所有ShopRegion，软删除
	 * @param regionID
	 * @return
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopRegionByRegionID(@DAOParam("regionID") int regionID);

	/**
	 * 软删除此shopID下所有ShopRegion
	 * @param shopID
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void deleteShopRegionByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 恢复此shopID下所有ShopRegion
	 * @param shopID
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void restoreShopRegionByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 按shopID和RegionID，软删除一个ShopRegion
	 * @param shopID
	 * @param regionID
	 * @return
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopRegionByShopIDAndRegionID(@DAOParam("shopID") int shopID, @DAOParam("regionID") int regionID);

	/**
	 * 更新ShopRegion
	 * @param shopRegion
	 * @return
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    int updateShopRegion(@DAOParam("shopRegion") ShopRegionEntity shopRegion);

	/**
	 * 批量添加ShopRegion
	 * @param shopRegionEntityList
	 */
	@DAOAction(action = DAOActionType.INSERT)
	void addShopRegionByList(@DAOParam("shopRegionList") List<ShopRegionEntity> shopRegionEntityList);

	/**
	 * 按ShopID直接删除相关ShopRegion
	 * @param shopId
	 */
	@DAOAction(action = DAOActionType.DELETE)
	void deleteShopRegionDirectlyByShopID(@DAOParam("shopID")int shopId);
}
