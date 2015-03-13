package com.dianping.rotate.shop.impl;

import com.dianping.rotate.shop.api.AdApolloShopRatingService;
import com.dianping.rotate.shop.dao.AdApolloShopRatingDAO;
import com.dianping.rotate.shop.dto.AdApolloShopRatingDTO;
import com.dianping.rotate.shop.json.AdApolloShopRatingEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sharon on 15-3-12.
 */
@Service("adApolloShopRatingService")
public class AdApolloShopRatingServiceImpl implements AdApolloShopRatingService {
    @Autowired
    AdApolloShopRatingDAO adApolloShopRatingDAO;

    public List<AdApolloShopRatingDTO> getApolloShopRating(int shopId, int bizId){
        List<AdApolloShopRatingDTO> result = new ArrayList<AdApolloShopRatingDTO>();
        List<AdApolloShopRatingEntity> ratingEntities = adApolloShopRatingDAO.queryAdApolloShopRating(shopId,bizId);
        if(CollectionUtils.isNotEmpty(ratingEntities)) {
            for(AdApolloShopRatingEntity ratingEntity : ratingEntities) {
                AdApolloShopRatingDTO ratingDTO = new AdApolloShopRatingDTO();
                entity2Dto(ratingDTO,ratingEntity);
                result.add(ratingDTO);
            }
            return result;
        }
        return null;
    }

    private void entity2Dto(AdApolloShopRatingDTO dto,AdApolloShopRatingEntity entity) {
        dto.setShopid(entity.getShopId());
        dto.setType(entity.getType());
        dto.setBizId(entity.getBizId());
        dto.setRating(entity.getRating());
    }
}
