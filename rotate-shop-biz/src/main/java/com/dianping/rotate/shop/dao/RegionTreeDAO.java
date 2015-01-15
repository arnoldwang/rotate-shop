package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.RegionTreeEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface RegionTreeDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<RegionTreeEntity> queryMainRegionTreeByRegionID(@DAOParam("regionID") int regionID);

}
