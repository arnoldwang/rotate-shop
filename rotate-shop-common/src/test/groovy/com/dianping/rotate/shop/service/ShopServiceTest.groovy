package com.dianping.rotate.shop.service

import com.dianping.rotate.shop.AbstractSpockTest
import com.dianping.rotate.shop.dao.ApolloShopDAO
import com.dianping.rotate.shop.dao.RotateGroupDAO
import com.dianping.rotate.shop.dao.RotateGroupShopDAO
import com.dianping.rotate.shop.entity.ApolloShopEntity
import com.dianping.rotate.shop.entity.RotateGroupEntity
import com.dianping.rotate.shop.entity.RotateGroupShopEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by yangjie on 1/13/15.
 */
class ShopServiceTest extends AbstractSpockTest {
    @Autowired
    ApolloShopDAO apolloShopDAO;

    @Autowired
    ShopService shopService;

    @Autowired
    RotateGroupDAO rotateGroupDAO;


    @Autowired
    RotateGroupShopDAO rotateGroupShopDAO;

    private int idSeed = 100;

    def getId() {
        return idSeed++;
    }

    def "test addPoiBySys with single shop"() {
        setup:
        String msg = "{'shopId': 500000, 'messageType': 4}";

        when:
        shopService.addPoiBySys(msg);

        then:
        def shop = apolloShopDAO.queryApolloShopByShopID(500000).get(0);
        500000 == shop.getShopID()
    }

    def "test addPoiBySys with mul shop"() {
        setup:
        String msg = "{'shopId': 500012, 'messageType': 4}";

        when:
        shopService.addPoiBySys(msg);

        then:
        def shop = apolloShopDAO.queryApolloShopByShopIDWithNoStatus(500012);
        500012 == shop.getShopID()
    }

    def "test addPoiByUser with wrong data"() {
        setup:
        String msg = "{'type': 201,'userId': -12345,'datetime': '2014-5-1'," +
                "'pair': [{'shopId': 12345,'shopUrl': 'http: //www.dianping.com/shop/12345'," +
                "'shopName': '小肥羊','branchName': '小肥羊','altName': 'XX'}]}";

        when:
        shopService.addPoiByUser(msg);

        then:
        1 == 1;
    }

    def "关闭连锁门店 在仅有1个门店的轮转组，门店组被删除"() {
        setup:
        RotateGroupEntity rotateGroup = createRotateGroup(false);
        RotateGroupShopEntity shop = createRotateGroupShop(rotateGroup.getId());

        when:
        shopService.closeShop(shop.getShopID());

        then:
        RotateGroupEntity currentRotateGroup = rotateGroupDAO.getRotateGroup(rotateGroup.getId());

        // 这个店被删掉了
        currentRotateGroup == null
    }

    def "关闭连锁门店 在仅有2个门店的轮转组， 门店组变为单店"() {
        setup:
        RotateGroupEntity rotateGroup = createRotateGroup(false);
        RotateGroupShopEntity shop1 = createRotateGroupShop(rotateGroup.getId());
        RotateGroupShopEntity shop2 = createRotateGroupShop(rotateGroup.getId());

        when:
        shopService.closeShop(shop1.getShopID());

        then:
        RotateGroupEntity currentRotateGroup = rotateGroupDAO.getRotateGroup(rotateGroup.getId());
        currentRotateGroup != null;
        currentRotateGroup.getType() == 0;
    }

    def "关闭连锁门店 在3个门店的轮转组， 门店组还是连锁店"() {
        setup:
        RotateGroupEntity rotateGroup = createRotateGroup(false);
        RotateGroupShopEntity shop1 = createRotateGroupShop(rotateGroup.getId());
        RotateGroupShopEntity shop2 = createRotateGroupShop(rotateGroup.getId());
        RotateGroupShopEntity shop3 = createRotateGroupShop(rotateGroup.getId());

        when:
        shopService.closeShop(shop1.getShopID());

        then:
        RotateGroupEntity currentRotateGroup = rotateGroupDAO.getRotateGroup(rotateGroup.getId());
        currentRotateGroup != null;
        currentRotateGroup.getType() == 1;
    }


    def "关闭连锁门店 在3个以上门店的轮转组， 门店组还是连锁店"() {
        setup:
        RotateGroupEntity rotateGroup = createRotateGroup(false);
        RotateGroupShopEntity shop1 = createRotateGroupShop(rotateGroup.getId());
        RotateGroupShopEntity shop2 = createRotateGroupShop(rotateGroup.getId());
        RotateGroupShopEntity shop3 = createRotateGroupShop(rotateGroup.getId());
        RotateGroupShopEntity shop4 = createRotateGroupShop(rotateGroup.getId());

        when:
        shopService.closeShop(shop1.getShopID());

        then:
        RotateGroupEntity currentRotateGroup = rotateGroupDAO.getRotateGroup(rotateGroup.getId());
        currentRotateGroup != null;
        currentRotateGroup.getType() == 1;
    }
    
    def "重开门店 如果门店组只有这1家店，门店组还是单店"() {
        setup:
        RotateGroupEntity rotateGroup = createRotateGroup(true);
        RotateGroupShopEntity shop = createRotateGroupShop(rotateGroup.getId());
        shopService.closeShop(shop.getShopID());

        when:
        shopService.openShop(shop.getShopID());

        then:
        RotateGroupEntity currentRotateGroup = rotateGroupDAO.getRotateGroup(rotateGroup.getId());

        currentRotateGroup != null
        currentRotateGroup.getType() == 0;
    }

    def "重开门店 如果门店组有2家店，门店组改为连锁店"() {
        setup:
        RotateGroupEntity rotateGroup = createRotateGroup(true);
        RotateGroupShopEntity shop1 = createRotateGroupShop(rotateGroup.getId());
        RotateGroupShopEntity shop2 = createRotateGroupShop(rotateGroup.getId());
        shopService.closeShop(shop1.getShopID());

        when:
        shopService.openShop(shop1.getShopID());

        then:
        RotateGroupEntity currentRotateGroup = rotateGroupDAO.getRotateGroup(rotateGroup.getId());

        currentRotateGroup != null
        currentRotateGroup.getType() == 1;
    }


    def createRotateGroup(boolean isSingle) {
        def rotateGroup = new RotateGroupEntity();
        rotateGroup.setBizID(1);
        rotateGroup.setType(isSingle ? 0 : 1);
        rotateGroup.setStatus(1);
        rotateGroup.setId(rotateGroupDAO.addToRotateGroup(rotateGroup));
        return rotateGroup;
    }
    

    def createRotateGroupShop(int rotateGroupId) {
        def shopId = getId();
        createShopEntity(shopId);

        def shop = new RotateGroupShopEntity();
        shop.setStatus(1);
        shop.setShopID(shopId);
        shop.setRotateGroupID(rotateGroupId);
        shop.setShopGroupID(getId());
        rotateGroupShopDAO.addToRotateGroupShop(shop);
        return shop;
    }

    def createShopEntity(int shopID) {
        def s = new ApolloShopEntity()
        s.setShopID(shopID)
        s.setShopGroupID(1)
        s.setCityID(1)
        s.setDistrict(1)
        s.setShopType(1)
        s.setShopStatus(5)
        apolloShopDAO.addApolloShop(s);
        return s;
    }
}
