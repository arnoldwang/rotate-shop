package com.dianping.rotate.shop.impl;

import com.dianping.combiz.entity.Region;
import com.dianping.combiz.util.CodeConstants;
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

    private Function<Region, RegionDTO> toRegionDTO = new Function<Region, RegionDTO>() {
        @Override
        public RegionDTO apply(Region from) {
            RegionDTO to = new RegionDTO();
            if (from != null) {
                to.setCityID((int) from.getCityId());
                to.setRegionID(from.getRegionId());
                to.setRegionName(from.getRegionName());
                to.setRegionType((int) from.getRegionType());
            }
            return to;
        }
    };


    @Override
    public RegionDTO getRegion(int regionID) {
        return toRegionDTO.apply(regionService.loadRegion(regionID));
    }

    // 现在地区都是3级的
    // 行政区 > 商区 > 地标
    @Override
    public List<RegionDTO> getRegionAncestors(int regionID) {
        Region region = regionService.loadRegion(regionID);

        int regionType = region.getRegionType();
        // 如果本身就是行政区，就直接返回自己
        if (regionType == CodeConstants.RegionType.District.value) {
            return Lists.transform(Lists.newArrayList(region), toRegionDTO);
        }

        // 如果是地标就调用相应方法
        if (regionType == CodeConstants.RegionType.Landmark.value) {
            return Lists.transform(regionService.findMainLandmarkPath(regionID), toRegionDTO);
        }

        // 如果是其它类型的话，则找一个行政区塞进去
        List<Region> ret = Lists.newArrayList();
        Region district = regionService.loadMainParentDistrict(regionID, true);
        if (district != null) {
            ret.add(district);
        }
        ret.add(region);
        return Lists.transform(ret, toRegionDTO);
    }

}
