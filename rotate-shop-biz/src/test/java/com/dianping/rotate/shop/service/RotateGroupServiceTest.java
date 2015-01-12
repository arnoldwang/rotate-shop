package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.AbstractTest;
import com.dianping.rotate.shop.api.ApolloShopService;
import com.dianping.rotate.shop.api.RotateGroupService;
import com.dianping.rotate.shop.dao.ApolloShopDAO;
import com.dianping.rotate.shop.dao.ApolloShopExtendDAO;
import com.dianping.rotate.shop.dao.ShopCategoryDAO;
import com.dianping.rotate.shop.dao.ShopRegionDAO;
import com.dianping.rotate.shop.dto.ApolloShopDTO;
import com.dianping.rotate.shop.dto.RotateGroupDTO;
import com.dianping.rotate.shop.dto.RotateGroupExtendDTO;
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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by luoming on 15/1/9.
 */
public class RotateGroupServiceTest extends AbstractTest {

    @Autowired
    private RotateGroupService rotateGroupService;

    @Before
    public void setUp() {

    }

    @Test
    public void getRotateGroup() {
        RotateGroupDTO rotateGroupDTO = rotateGroupService.getRotateGroup(1);

        Assert.assertEquals(1, (int) rotateGroupDTO.getId());
    }

}
