package com.dianping.rotate.shop.impl;

import com.dianping.combiz.entity.Region;
import com.dianping.rotate.shop.api.RegionService;
import com.dianping.rotate.shop.dto.RegionDTO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luoming on 15/1/7.
 */
@Service("apolloRegionService")
public class RegionServiceImpl implements RegionService {
    @Autowired
    com.dianping.combiz.service.RegionService regionService;

    @Override
    public RegionDTO getRegion(int regionID) {
        return fromEntity(regionService.loadRegion(regionID));
    }

    @Override
    public List<RegionDTO> getRegionAncestors(int regionID) {
        return Lists.transform(regionService.findMainLandmarkPath(regionID), new Function<Region, RegionDTO>() {
            @Override
            public RegionDTO apply(Region region) {
                return fromEntity(region);
            }
        });
    }

    private RegionDTO fromEntity(Region from) {
        RegionDTO to = new RegionDTO();
        if (from != null) {
            to.setCityID((int) from.getCityId());
            to.setRegionID(from.getRegionId());
            to.setRegionName(from.getRegionName());
            to.setRegionType((int) from.getRegionType());
        }
        return to;
    }

}
