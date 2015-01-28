package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.dao.ApolloShopBusinessStatusDAO;
import com.dianping.rotate.shop.json.ApolloShopBusinessStatusEntity;
import com.dianping.rotate.shop.service.ShopBusinessStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luoming on 15/1/27.
 */
@Service("shopBusinessStatusService")
public class ShopBusinessStatusServiceImpl implements ShopBusinessStatusService {

    @Autowired
    private ApolloShopBusinessStatusDAO apolloShopBusinessStatusDAO;

    @Override
    public void deleteApolloShopBusinessStatusAll() {
        apolloShopBusinessStatusDAO.deleteApolloShopBusinessStatusAll();
    }

    @Override
    public void addApolloShopBusinessStatus(List<ApolloShopBusinessStatusEntity> apolloShopBusinessStatusEntityList) {
        apolloShopBusinessStatusDAO.addApolloShopBusinessStatusBatch(apolloShopBusinessStatusEntityList);
    }

    @Override
    public void clearApolloShopBusinessStatus() {
        apolloShopBusinessStatusDAO.clearApolloShopBusinessStatus();
    }
}
