package com.dianping.rotate.shop.api;

import com.dianping.core.type.PageModel;
import com.dianping.rotate.shop.dto.ApolloShopForTerritoryQueryDTO;


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
}
