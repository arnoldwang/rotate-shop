package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.ShopCategoryEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ShopCategoryDAO extends GenericDao {

	/**
	 * 查找与此CategoryID相关的所有ShopCategory
	 * @param categoryID
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopCategoryByCategoryID(@DAOParam("categoryID") int categoryID);

	/**
	 * 查找此门店所有的ShopCategory
	 * @param shopID
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopCategoryByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 查询此门店列表的ShopCategory
	 * @param shopIDList
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopCategoryByShopIDList(@DAOParam("shopIDList") List<Integer> shopIDList);

	/**
	 * 查询此门店的主Category
	 * @param shopID
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopMainCategoryByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 查询门店列表中的主Category
	 * @param shopIDList
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopMainCategoryByShopIDList(@DAOParam("shopIDList") List<Integer> shopIDList);

	/**
	 * 按ShopID和CategoryID查找ShopCategory
	 * @param shopID
	 * @param categoryID
	 * @return
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ShopCategoryEntity> queryShopCategoryByShopIDAndCategoryID(@DAOParam("shopID") int shopID, @DAOParam("categoryID") int categoryID);

	/**
	 * 新加ShopCategory
	 * @param shopCategory
	 * @return
	 */
    @DAOAction(action = DAOActionType.INSERT)
    int addShopCategory(@DAOParam("shopCategory") ShopCategoryEntity shopCategory);

	/**
	 * 关闭与此CategoryID相关的ShopCategory
	 * @param categoryID
	 * @return
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopCategoryByCategoryID(@DAOParam("categoryID") int categoryID);

	/**
	 * 关闭此shopID下所有的ShopCategory
	 * @param shopID
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void deleteShopCategoryByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 重新开启此shopID下所有ShopCategory
	 * @param shopID
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void restoreShopCategoryByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 按shopID和CategoryID软删除一个ShopCategory
	 * @param shopID
	 * @param categoryID
	 * @return
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopCategoryByShopIDAndCategoryID(@DAOParam("shopID") int shopID, @DAOParam("categoryID") int categoryID);

	/**
	 * 更新ShopCategory
	 * @param shopCategory
	 * @return
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    int updateShopCategory(@DAOParam("shopCategory") ShopCategoryEntity shopCategory);

	/**
	 * 批量新加ShopCategory
	 * @param shopCategoryEntityList
	 */
	@DAOAction(action = DAOActionType.INSERT)
	void addShopCategoryByList(@DAOParam("shopCategoryList") List<ShopCategoryEntity> shopCategoryEntityList);

	/**
	 * 硬删除shopID下所有ShopCategory
	 * @param shopId
	 */
	@DAOAction(action = DAOActionType.DELETE)
	void deleteShopCategoryDirectlyByShopID(@DAOParam("shopID")int shopId);
}
