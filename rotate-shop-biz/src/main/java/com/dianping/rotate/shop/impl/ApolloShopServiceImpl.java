package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.ApolloShopService;
import com.dianping.rotate.shop.dao.ApolloShopDAO;
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO;
import com.dianping.rotate.shop.dao.ShopCategoryDAO;
import com.dianping.rotate.shop.dao.ShopRegionDAO;
import com.dianping.rotate.shop.dto.ApolloShopDTO;
import com.dianping.rotate.shop.dto.ShopCategoryDTO;
import com.dianping.rotate.shop.dto.ShopRegionDTO;
import com.dianping.rotate.shop.entity.ApolloShopEntity;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;
import com.dianping.rotate.shop.entity.ShopCategoryEntity;
import com.dianping.rotate.shop.entity.ShopRegionEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by luoming on 15/1/6.
 */

@Service("apolloShopService")
public class ApolloShopServiceImpl implements ApolloShopService {

    @Autowired
    private ApolloShopDAO apolloShopDAO;

    @Autowired
    private ApolloShopExtendDAO apolloShopExtendDAO;

    @Autowired
    private ShopRegionDAO shopRegionDAO;

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    @Override
    public ApolloShopDTO getApolloShop(int shopID, int bizID) {
        ApolloShopDTO apolloShopDTO = new ApolloShopDTO();
        processShopExtend(apolloShopDTO, shopID, bizID);
        processShop(apolloShopDTO, shopID);
        processRegion(apolloShopDTO, shopID);
        processCategory(apolloShopDTO, shopID);
        return apolloShopDTO;
    }

    @Override
    public List<ApolloShopDTO> getApolloShop(List<Integer> shopIDList, int bizID) {
        List<ApolloShopDTO> apolloShopDTOList = new ArrayList<ApolloShopDTO>();
        if(CollectionUtils.isNotEmpty(shopIDList) && shopIDList.size() <= 1000) {
            processShop(apolloShopDTOList, shopIDList);
            if(CollectionUtils.isNotEmpty(apolloShopDTOList)) {
                Map<Integer, ApolloShopDTO> apolloShopDTOMap = new HashMap<Integer, ApolloShopDTO>();
                transShopListToMap(apolloShopDTOList, apolloShopDTOMap);
                processShopExtend(apolloShopDTOMap, shopIDList, bizID);
                processRegion(apolloShopDTOMap, shopIDList);
                processCategory(apolloShopDTOMap, shopIDList);
                apolloShopDTOList.clear();
                apolloShopDTOList = (List<ApolloShopDTO>) apolloShopDTOMap.values();
            }
        }
        return apolloShopDTOList;
    }

    @Override
    public void deleteApolloShopByShopID(int shopId) {
        apolloShopDAO.deleteApolloShopByShopID(shopId);
    }

    @Override
    public void restoreApolloShopByShopID(int shopId) {
        apolloShopDAO.restoreApolloShopByShopID(shopId);
    }

    private void processShop(ApolloShopDTO apolloShopDTO, int shopID) {
        List<ApolloShopEntity> apolloShopList = apolloShopDAO.queryApolloShopByShopID(shopID);
        if(CollectionUtils.isNotEmpty(apolloShopList)) {
            transShopEntityToDTO(apolloShopList.get(0), apolloShopDTO);
        }
    }

    private void processShop(List<ApolloShopDTO> apolloShopDTOList, List<Integer> shopIDList) {
        List<ApolloShopEntity> apolloShopEntityList = apolloShopDAO.queryApolloShopByShopIDList(shopIDList);
        if(CollectionUtils.isNotEmpty(apolloShopEntityList)) {
            for(ApolloShopEntity apolloShopEntity : apolloShopEntityList) {
                ApolloShopDTO apolloShopDTO = new ApolloShopDTO();
                transShopEntityToDTO(apolloShopEntity, apolloShopDTO);
                apolloShopDTOList.add(apolloShopDTO);
            }
        }
    }

    private void transShopEntityToDTO(ApolloShopEntity apolloShopEntity, ApolloShopDTO apolloShopDTO) {
        apolloShopDTO.setShopID(apolloShopEntity.getShopID());
        apolloShopDTO.setShopGroupID(apolloShopEntity.getShopGroupID());
        apolloShopDTO.setCityID(apolloShopEntity.getCityID());
        apolloShopDTO.setDistrict(apolloShopEntity.getDistrict());
        apolloShopDTO.setShopType(apolloShopEntity.getShopType());
        apolloShopDTO.setShopStatus(apolloShopEntity.getShopStatus());
    }

    private void transShopListToMap(List<ApolloShopDTO> apolloShopDTOList, Map<Integer, ApolloShopDTO> apolloShopDTOMap) {
        for(ApolloShopDTO apolloShopDTO : apolloShopDTOList) {
            apolloShopDTOMap.put(apolloShopDTO.getShopID(), apolloShopDTO);
        }
    }

    private void processShopExtend(ApolloShopDTO apolloShopDTO, int shopID, int bizID) {
        List<ApolloShopExtendEntity> apolloShopExtendList = apolloShopExtendDAO.queryApolloShopExtendByShopIDAndBizID(shopID, bizID);
        if(CollectionUtils.isNotEmpty(apolloShopExtendList)) {
            transShopExtendEntityToDTO(apolloShopExtendList.get(0), apolloShopDTO);
        }
    }

    private void processShopExtend(Map<Integer, ApolloShopDTO> apolloShopDTOMap, List<Integer> shopIDList, int bizID) {
        List<ApolloShopExtendEntity> apolloShopExtendList = apolloShopExtendDAO.queryApolloShopExtendByShopIDListAndBizID(shopIDList, bizID);
        if(CollectionUtils.isNotEmpty(apolloShopExtendList)) {
            for(ApolloShopExtendEntity apolloShopExtendEntity : apolloShopExtendList) {
                int shopID = apolloShopExtendEntity.getShopID();
                ApolloShopDTO apolloShopDTO = apolloShopDTOMap.get(shopID);
                if(apolloShopDTO != null) {
                    transShopExtendEntityToDTO(apolloShopExtendEntity, apolloShopDTO);
                }
            }
        }
    }

    private void transShopExtendEntityToDTO(ApolloShopExtendEntity apolloShopExtendEntity, ApolloShopDTO apolloShopDTO) {
        apolloShopDTO.setBizID(apolloShopExtendEntity.getBizID());
        apolloShopDTO.setType(apolloShopExtendEntity.getType());
        apolloShopDTO.setRating(apolloShopExtendEntity.getRating());
        apolloShopDTO.setShopExtendStatus(apolloShopExtendEntity.getStatus());
    }

    private void processRegion(ApolloShopDTO apolloShopDTO, int shopID) {
        List<ShopRegionEntity> shopRegionList = shopRegionDAO.queryShopRegionByShopID(shopID);
        if(CollectionUtils.isNotEmpty(shopRegionList)) {
            List<ShopRegionDTO> shopRegionDTOList = new ArrayList<ShopRegionDTO>();
            for(ShopRegionEntity shopRegionEntity : shopRegionList) {
                ShopRegionDTO shopRegionDTO = new ShopRegionDTO();
                shopRegionDTO.setRegionID(shopRegionEntity.getRegionID());
                shopRegionDTO.setShopID(shopRegionEntity.getShopID());
                shopRegionDTO.setIsMain(shopRegionEntity.getIsMain());
                shopRegionDTOList.add(shopRegionDTO);
            }
            apolloShopDTO.setShopRegionList(shopRegionDTOList);
        }
    }

    private void processRegion(Map<Integer, ApolloShopDTO> apolloShopDTOMap, List<Integer> shopIDList) {
        List<ShopRegionEntity> shopRegionList = shopRegionDAO.queryShopRegionByShopIDList(shopIDList);
        if(CollectionUtils.isNotEmpty(shopRegionList)) {
            Map<Integer, List<ShopRegionDTO>> shopRegionDTOListMap = new HashMap<Integer, List<ShopRegionDTO>>();
            for(ShopRegionEntity shopRegionEntity : shopRegionList) {
                ShopRegionDTO shopRegionDTO = new ShopRegionDTO();
                shopRegionDTO.setRegionID(shopRegionEntity.getRegionID());
                shopRegionDTO.setShopID(shopRegionEntity.getShopID());
                shopRegionDTO.setIsMain(shopRegionEntity.getIsMain());
                if(shopRegionDTOListMap.get(shopRegionEntity.getShopID()) == null) {
                    List<ShopRegionDTO> shopRegionDTOList = new ArrayList<ShopRegionDTO>();
                    shopRegionDTOList.add(shopRegionDTO);
                } else {
                    shopRegionDTOListMap.get(shopRegionEntity.getShopID()).add(shopRegionDTO);
                }
            }
            Iterator<Integer> shopIDIterator = shopRegionDTOListMap.keySet().iterator();
            while(shopIDIterator.hasNext()) {
                int shopID = shopIDIterator.next();
                ApolloShopDTO apolloShopDTO = apolloShopDTOMap.get(shopID);
                if(apolloShopDTO != null) {
                    apolloShopDTO.setShopRegionList(shopRegionDTOListMap.get(shopID));
                }
            }
        }
    }

    private void processCategory(ApolloShopDTO apolloShopDTO, int shopID) {
        List<ShopCategoryEntity> shopCategoryList = shopCategoryDAO.queryShopCategoryByShopID(shopID);
        if(CollectionUtils.isNotEmpty(shopCategoryList)) {
            List<ShopCategoryDTO> shopCategoryDTOList = new ArrayList<ShopCategoryDTO>();
            for(ShopCategoryEntity shopCategoryEntity : shopCategoryList) {
                ShopCategoryDTO shopCategoryDTO = new ShopCategoryDTO();
                shopCategoryDTO.setCategoryID(shopCategoryEntity.getCategoryID());
                shopCategoryDTO.setShopID(shopCategoryEntity.getShopID());
                shopCategoryDTO.setIsMain(shopCategoryEntity.getIsMain());
                shopCategoryDTOList.add(shopCategoryDTO);
            }
            apolloShopDTO.setShopCategoryList(shopCategoryDTOList);
        }
    }

    private void processCategory(Map<Integer, ApolloShopDTO> apolloShopDTOMap, List<Integer> shopIDList) {
        List<ShopCategoryEntity> shopCategoryEntityList = shopCategoryDAO.queryShopCategoryByShopIDList(shopIDList);
        if(CollectionUtils.isNotEmpty(shopCategoryEntityList)) {
            Map<Integer, List<ShopCategoryDTO>> shopCategoryDTOListMap = new HashMap<Integer, List<ShopCategoryDTO>>();
            for(ShopCategoryEntity shopCategoryEntity : shopCategoryEntityList) {
                ShopCategoryDTO shopCategoryDTO = new ShopCategoryDTO();
                shopCategoryDTO.setCategoryID(shopCategoryEntity.getCategoryID());
                shopCategoryDTO.setShopID(shopCategoryEntity.getShopID());
                shopCategoryDTO.setIsMain(shopCategoryEntity.getIsMain());
                if(shopCategoryDTOListMap.get(shopCategoryEntity.getShopID()) == null) {
                    List<ShopCategoryDTO> shopCategoryDTOList = new ArrayList<ShopCategoryDTO>();
                    shopCategoryDTOList.add(shopCategoryDTO);
                } else {
                    shopCategoryDTOListMap.get(shopCategoryEntity.getShopID()).add(shopCategoryDTO);
                }
            }
            Iterator<Integer> shopIDIterator = shopCategoryDTOListMap.keySet().iterator();
            while(shopIDIterator.hasNext()) {
                int shopID = shopIDIterator.next();
                ApolloShopDTO apolloShopDTO = apolloShopDTOMap.get(shopID);
                if(apolloShopDTO != null) {
                    apolloShopDTO.setShopCategoryList(shopCategoryDTOListMap.get(shopID));
                }
            }
        }
    }

}
