package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.AbstractTest;
import com.dianping.rotate.shop.dao.RotateGroupDAO;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.json.RotateGroupEntity;
import com.dianping.rotate.shop.json.RotateGroupShopEntity;
import com.dianping.rotate.shop.json.ShopJson;
import com.dianping.rotate.shop.producer.RotateGroupShopMessageProducer;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zaza on 15/3/11.
 */
public class RotateGroupMessageAspectTest extends AbstractTest {
    @Autowired
    RotateGroupDAO rotateGroupDAO;
    @Autowired
    RotateGroupShopDAO rotateGroupShopDAO;
    @Autowired
    RotateGroupShopMessageProducer producer;

    @Test
    public void rotateGroupInsertAspectTest(){
        RotateGroupEntity rotateGroupEntity = new RotateGroupEntity();
        rotateGroupEntity.setBizID(1000);
        rotateGroupEntity.setStatus(1);
        rotateGroupEntity.setType(1);
        rotateGroupDAO.addToRotateGroup(rotateGroupEntity);
    }

    @Test
    public void rotateGroupUpdateForRestoreAspectTest(){

        rotateGroupDAO.restoreRotateGroup(96757806);
    }

    @Test
    public void rotateGroupUpdateAspectTest(){
        RotateGroupEntity rotateGroupEntity = new RotateGroupEntity();
        rotateGroupEntity.setId(96757806);
        rotateGroupEntity.setBizID(1001);
        rotateGroupEntity.setStatus(1);
        rotateGroupEntity.setType(1);
        rotateGroupDAO.updateRotateGroup(rotateGroupEntity);
    }

    @Test
    public void rotateGroupDeleteAspectTest(){
        rotateGroupDAO.deleteRotateGroup(96757806);
        List<Integer> rotateGroupIds = Lists.newArrayList(96757806,96757807);
        rotateGroupDAO.deleteRotateGroupBatch(rotateGroupIds);
    }

    @Test
    public void rotateGroupShopInsertAspectTest(){
        RotateGroupShopEntity rotateGroupShopEntity = new RotateGroupShopEntity();
        rotateGroupShopEntity.setStatus(1);
        rotateGroupShopEntity.setRotateGroupID(-1);
        rotateGroupShopEntity.setShopGroupID(1111);
        rotateGroupShopEntity.setShopID(1111);
        rotateGroupShopDAO.addToRotateGroupShop(rotateGroupShopEntity);
    }

    @Test
    public void rotateGroupShopBatchInsertAspectTest(){
        List<RotateGroupShopEntity> rotateGroupShopEntities = Lists.newArrayList();
        RotateGroupShopEntity rotateGroupShopEntity = new RotateGroupShopEntity();
        rotateGroupShopEntity.setStatus(1);
        rotateGroupShopEntity.setRotateGroupID(-1);
        rotateGroupShopEntity.setShopGroupID(1111);
        rotateGroupShopEntity.setShopID(1111);
        RotateGroupShopEntity rotateGroupShopEntity2 = new RotateGroupShopEntity();
        rotateGroupShopEntity2.setStatus(1);
        rotateGroupShopEntity2.setRotateGroupID(-2);
        rotateGroupShopEntity2.setShopGroupID(1111);
        rotateGroupShopEntity2.setShopID(1111);
        rotateGroupShopEntities.add(rotateGroupShopEntity);
        rotateGroupShopEntities.add(rotateGroupShopEntity2);
        rotateGroupShopDAO.addToRotateGroupShopByList(rotateGroupShopEntities);
    }

    @Test
    public void rotateGroupShopUpdateAspectTest(){

//        rotateGroupShopDAO.restoreRotateGroupShopByShopId(-1111);
//        RotateGroupShopEntity rotateGroupShopEntity = new RotateGroupShopEntity();
//        rotateGroupShopEntity.setShopID(-2222);
//        rotateGroupShopEntity.setId(102605471);
//        rotateGroupShopEntity.setShopGroupID(-1111);
//        rotateGroupShopDAO.updateRotateGroupShop(rotateGroupShopEntity);
//        rotateGroupShopDAO.updateRotateGroupShopByShopID(-1111,-1111);

//        rotateGroupShopDAO.updateRotateGroupShopRotateGroupIDBatch(-3333,Lists.newArrayList(-1,-2));
          rotateGroupShopDAO.deleteRotateGroupShop(102605471);
    }

    @Test
    public void rotateGroupShopDeleteAspectTest(){

    }

    @Test
    public void RotateGroupMessageProducerTest(){
        List<Integer> shops = Lists.newArrayList();
        shops.add(11111);
        //producer.send(123,-123,shops,234,-234);
    }
}
