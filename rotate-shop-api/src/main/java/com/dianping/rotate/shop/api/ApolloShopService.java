package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.ApolloShopDTO;

import java.util.List;

/**
 * Created by luoming on 15/1/6.
 */
public interface ApolloShopService {

    /**
     * 获取阿波罗门店信息
     * @param shopID
     * @param bizID
     * @return
     */
    public ApolloShopDTO getApolloShop(int shopID, int bizID);

    /**
     * 批量获取阿波罗门店信息（最大限制一次获取量为10000）
     * @param shopIDList
     * @param bizID
     * @return
     */
    public List<ApolloShopDTO> getApolloShop(List<Integer> shopIDList, int bizID);

    public void deleteApolloShopByShopID(int shopId);
    public void restoreApolloShopByShopID(int shopId);

}
