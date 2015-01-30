package com.dianping.rotate.shop.service.impl;

import com.dianping.rotate.shop.dao.ApolloShopExtendDAO;
import com.dianping.rotate.shop.json.ApolloShopExtendEntity;
import com.dianping.rotate.shop.service.ShopExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luoming on 15/1/28.
 */
@Service("shopExtendService")
public class ShopExtendServiceImpl implements ShopExtendService {

    @Autowired
    private ApolloShopExtendDAO apolloShopExtendDAO;

    @Override
    public void updateApolloShopExtendRating(List<ApolloShopExtendEntity> apolloShopExtendEntityList) {
        apolloShopExtendDAO.updateApolloShopExtendRating(apolloShopExtendEntityList);
    }

}
