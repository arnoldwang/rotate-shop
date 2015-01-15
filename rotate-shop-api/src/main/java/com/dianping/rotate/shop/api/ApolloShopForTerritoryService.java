package com.dianping.rotate.shop.api;

import com.dianping.core.type.PageModel;
import com.dianping.rotate.shop.dto.ApolloShopForTerritoryQueryDTO;

import java.util.List;


/**
 *  提供给战区系统获取门店信息的服务
 *  Created by dev_wzhang on 15-1-8.
 */
public interface ApolloShopForTerritoryService {

    /**
     * 批量获取门店信息
     * @param queryDto：查询条件
     * @return PageModel返回分页数据
     */
    PageModel batchFetchApolloShop(ApolloShopForTerritoryQueryDTO queryDto);

    /**
     * 批量获取门店ID信息
     * @param where：查询条件
     * @param startLimit:limit的起始值
     * @param endLimit:limit的结束值
     * @return 符合条件的ID集合
     */
    List<Integer> batchFetchApolloShopID(String where,int startLimit,int endLimit);
}
