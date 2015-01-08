package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.RegionDTO;

import java.util.List;

/**
 * Created by luoming on 15/1/6.
 */
public interface RegionService {

    public RegionDTO getRegion(int regionID);

    public List<RegionDTO> getRegionAncestors(int regionID);

}
