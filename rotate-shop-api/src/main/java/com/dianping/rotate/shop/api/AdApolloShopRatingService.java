package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.AdApolloShopRatingDTO;

import java.util.List;

/**
 * Created by sharon on 15-3-5.
 */
public interface AdApolloShopRatingService {
    List<AdApolloShopRatingDTO> getApolloShopRating(int shopId,int bizId);
}
