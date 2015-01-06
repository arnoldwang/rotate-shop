package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.RegionDTO;

/**
 * Created by luoming on 15/1/6.
 */
public interface RegionService {

    public RegionDTO getRegionByRegionIDAndCityID(int regionID, int cityID);

}
