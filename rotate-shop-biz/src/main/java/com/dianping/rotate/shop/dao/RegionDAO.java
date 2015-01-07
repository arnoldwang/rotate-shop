package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.ApolloShopEntity;
import com.dianping.rotate.shop.entity.RegionEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface RegionDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<RegionEntity> queryRegionByRegionID(@DAOParam("regionID") int regionID);

    @DAOAction(action = DAOActionType.QUERY)
    List<RegionEntity> queryRegionByRegionIDAndCityID(@DAOParam("regionID") int regionID, @DAOParam("cityID") int cityID);

    @DAOAction(action = DAOActionType.INSERT)
    int addRegion(@DAOParam("region") RegionEntity region);

    @DAOAction(action = DAOActionType.UPDATE)
    int deleteRegionByRegionID(@DAOParam("regionID") int regionID);

    @DAOAction(action = DAOActionType.UPDATE)
    int updateRegion(@DAOParam("region") RegionEntity region);

}
