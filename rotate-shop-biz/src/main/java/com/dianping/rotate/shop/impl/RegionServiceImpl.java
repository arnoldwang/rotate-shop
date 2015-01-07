package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.CategoryService;
import com.dianping.rotate.shop.api.RegionService;
import com.dianping.rotate.shop.dao.RegionDAO;
import com.dianping.rotate.shop.dto.CategoryDTO;
import com.dianping.rotate.shop.dto.RegionDTO;
import com.dianping.rotate.shop.entity.RegionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luoming on 15/1/7.
 */
@Service("apolloRegionService")
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDAO regionDAO;

    @Override
    public RegionDTO getRegionByRegionIDAndCityID(int regionID, int cityID) {
        RegionDTO regionDTO = new RegionDTO();
        List<RegionEntity> regionList = regionDAO.queryRegionByRegionIDAndCityID(regionID, cityID);
        if(regionList != null && regionList.size() != 0) {
            RegionEntity region = regionList.get(0);
            regionDTO.setCityID(region.getCityID());
            regionDTO.setRegionID(region.getRegionID());
            regionDTO.setRegionName(region.getRegionName());
            regionDTO.setRegionType(region.getRegionType());
        }
        return regionDTO;
    }

}
