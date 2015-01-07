package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.RegionService;
import com.dianping.rotate.shop.dao.RegionDAO;
import com.dianping.rotate.shop.dao.RegionTreeDAO;
import com.dianping.rotate.shop.dto.RegionDTO;
import com.dianping.rotate.shop.entity.RegionEntity;
import com.dianping.rotate.shop.entity.RegionTreeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoming on 15/1/7.
 */
@Service("apolloRegionService")
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDAO regionDAO;

    @Autowired
    private RegionTreeDAO regionTreeDAO;

    @Override
    public RegionDTO getRegionByRegionIDAndCityID(int regionID, int cityID) {
        return transRegionEntityToDTO(getRegionEntityByRegionIDAndCityID(regionID, cityID));
    }

    @Override
    public List<RegionDTO> getRegionTreeByRegionIDAndCityID(int regionID, int cityID) {
        List<RegionDTO> regionDTOList = new ArrayList<RegionDTO>();
        RegionEntity region = getRegionEntityByRegionIDAndCityID(regionID, cityID);
        getParentRegionByRegion(regionDTOList, region);
        return regionDTOList;
    }

    private RegionEntity getRegionEntityByRegionIDAndCityID(int regionID, int cityID) {
        List<RegionEntity> regionList = regionDAO.queryRegionByRegionIDAndCityID(regionID, cityID);
        if(regionList != null && regionList.size() != 0) {
            return regionList.get(0);
        }
        return null;
    }

    private RegionDTO transRegionEntityToDTO(RegionEntity region) {
        if(region == null) {
            return null;
        }
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setCityID(region.getCityID());
        regionDTO.setRegionID(region.getRegionID());
        regionDTO.setRegionName(region.getRegionName());
        regionDTO.setRegionType(region.getRegionType());
        return regionDTO;
    }

    private void getParentRegionByRegion(List<RegionDTO> regionDTOList, RegionEntity region) {
        if(region != null) {
            regionDTOList.add(0, transRegionEntityToDTO(region));
            List<RegionTreeEntity> regionTreeList = regionTreeDAO.queryMainRegionTreeByRegionID(region.getRegionID());
            if(regionTreeList != null && regionTreeList.size() != 0) {
                RegionTreeEntity regionTree = regionTreeList.get(0);
                getParentRegionByRegion(regionDTOList,
                        getRegionEntityByRegionIDAndCityID(regionTree.getParentID(), region.getCityID()));
            }
        }
    }

}
