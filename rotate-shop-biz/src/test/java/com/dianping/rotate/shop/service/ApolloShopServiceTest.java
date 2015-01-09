package com.dianping.rotate.shop.service;

import static org.mockito.Mockito.*;

import com.dianping.rotate.shop.AbstractTest;
import com.dianping.rotate.shop.api.ApolloShopService;
import com.dianping.rotate.shop.dao.ApolloShopDAO;
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO;
import com.dianping.rotate.shop.dao.ShopCategoryDAO;
import com.dianping.rotate.shop.dao.ShopRegionDAO;
import com.dianping.rotate.shop.dto.ApolloShopDTO;
import com.dianping.rotate.shop.entity.ApolloShopEntity;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;
import com.dianping.rotate.shop.entity.ShopCategoryEntity;
import com.dianping.rotate.shop.entity.ShopRegionEntity;
import com.dianping.rotate.shop.impl.ApolloShopServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoming on 15/1/9.
 */
public class ApolloShopServiceTest {

    @InjectMocks
    private ApolloShopService apolloShopService = new ApolloShopServiceImpl();

    @Mock
    private ApolloShopDAO apolloShopDAO;

    @Mock
    private ApolloShopExtendDAO apolloShopExtendDAO;

    @Mock
    private ShopRegionDAO shopRegionDAO;

    @Mock
    private ShopCategoryDAO shopCategoryDAO;

    @Before
    public void setUp() {

    }

    @Test
    public void getApolloShop() {
        // ApolloShopDAO
        ApolloShopDAO apolloShopDAO = mock(ApolloShopDAO.class);

        List<ApolloShopEntity> apolloShopEntityList = new ArrayList<ApolloShopEntity>();
        ApolloShopEntity apolloShopEntity = new ApolloShopEntity();
        apolloShopEntity.setShopID(1);

        when(apolloShopDAO.queryApolloShopByShopID(1)).thenReturn(apolloShopEntityList);

        // ApollpShopExtendDAO
        ApolloShopExtendDAO apolloShopExtendDAO = mock(ApolloShopExtendDAO.class);

        List<ApolloShopExtendEntity> apolloShopExtendEntityList = new ArrayList<ApolloShopExtendEntity>();
        ApolloShopExtendEntity apolloShopExtendEntity = new ApolloShopExtendEntity();
        apolloShopExtendEntity.setShopID(1);
        apolloShopExtendEntity.setBizID(1);

        when(apolloShopExtendDAO.queryApolloShopExtendByShopIDAndBizID(1, 1)).thenReturn(apolloShopExtendEntityList);

        // ShopRegionDAO
        ShopRegionDAO shopRegionDAO = mock(ShopRegionDAO.class);

        List<ShopRegionEntity> shopRegionEntityList = new ArrayList<ShopRegionEntity>();
        ShopRegionEntity shopRegionEntity = new ShopRegionEntity();
        shopRegionEntity.setShopID(1);
        shopRegionEntity.setRegionID(10);

        when(shopRegionDAO.queryShopRegionByShopID(1)).thenReturn(shopRegionEntityList);

        // ShopCategoryDAO
        ShopCategoryDAO shopCategoryDAO = mock(ShopCategoryDAO.class);

        List<ShopCategoryEntity> shopCategoryEntityList = new ArrayList<ShopCategoryEntity>();
        ShopCategoryEntity shopCategoryEntity = new ShopCategoryEntity();
        shopCategoryEntity.setShopID(1);
        shopCategoryEntity.setCategoryID(100);

        when(shopCategoryDAO.queryShopCategoryByShopID(1)).thenReturn(shopCategoryEntityList);

        ApolloShopService apolloShopService = new ApolloShopServiceImpl();

        ApolloShopDTO apolloShopDTO = apolloShopService.getApolloShop(1, 1);

        verify(shopCategoryDAO, times(1)).queryShopCategoryByShopID(1);

        Assert.assertEquals(1, (int) apolloShopDTO.getShopID());
        Assert.assertEquals(1, (int) apolloShopDTO.getBizID());
        Assert.assertEquals(10, (int) apolloShopDTO.getMainRegionID());
        Assert.assertEquals(100, (int) apolloShopDTO.getMainCategoryID());
    }

}
