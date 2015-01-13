package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.RegionEntity;
import com.dianping.rotate.shop.entity.ShopRegionEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ShopRegionDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopRegionByRegionID(@DAOParam("regionID") int regionID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopRegionByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopMainRegionByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ShopRegionEntity> queryShopRegionByShopIDAndRegionID(@DAOParam("shopID") int shopID, @DAOParam("regionID") int regionID);

    @DAOAction(action = DAOActionType.INSERT)
    int addShopRegion(@DAOParam("shopRegion") ShopRegionEntity shopRegion);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopRegionByRegionID(@DAOParam("regionID") int regionID);

    @DAOAction(action = DAOActionType.UPDATE)
    void deleteShopRegionByShopID(@DAOParam("shopID") int shopID);


    @DAOAction(action = DAOActionType.UPDATE)
    void restoreShopRegionByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteShopRegionByShopIDAndRegionID(@DAOParam("shopID") int shopID, @DAOParam("regionID") int regionID);

    @DAOAction(action = DAOActionType.UPDATE)
    int updateShopRegion(@DAOParam("shopRegion") ShopRegionEntity shopRegion);

	@DAOAction(action = DAOActionType.INSERT)
	void addShopRegionByList(@DAOParam("shopRegionList") List<ShopRegionEntity> shopRegionEntityList);
}
