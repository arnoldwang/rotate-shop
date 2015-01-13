package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.ApolloShopDTO;

/**
 * Created by luoming on 15/1/6.
 */
public interface ApolloShopService {

    /**
     * 获取阿波罗门店相关信息
     * @param shopID
     * @param bizID
     * @return
     */
    public ApolloShopDTO getApolloShop(int shopID, int bizID);

    public void deleteApolloShopByShopID(int shopId);
    public void restoreApolloShopByShopID(int shopId);

}
