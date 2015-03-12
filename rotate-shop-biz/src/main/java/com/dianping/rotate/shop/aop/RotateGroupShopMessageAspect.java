package com.dianping.rotate.shop.aop;

import com.dianping.avatar.dao.DAOMethod;
import com.dianping.rotate.shop.dao.RotateGroupShopDAO;
import com.dianping.rotate.shop.json.RotateGroupShopEntity;
import com.dianping.rotate.shop.producer.RotateGroupShopMessageProducer;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zaza on 15/3/11.
 */
@Aspect
@Component
public class RotateGroupShopMessageAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("rotateGroupShopMessageProducer")
    RotateGroupShopMessageProducer producer;

    @Autowired
    RotateGroupShopDAO rotateGroupShopDAO;

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeInsert(*))&&args(daoMethod)")
    public void doAfterInsert(DAOMethod daoMethod){
        try{
            if(daoMethod.getName().equals("addToRotateGroupShop")){
                Integer rotateGroupId = ((RotateGroupShopEntity)daoMethod.getParams().get("rotateGroupShop")).getRotateGroupID();
                producer.send(getMessage("insert",rotateGroupId));
            }
            if(daoMethod.getName().equals("addToRotateGroupShopByList")){
                List<RotateGroupShopEntity> rotateGroupShopEntitys = (List<RotateGroupShopEntity>)daoMethod.getParams().get("rotateGroupShopList");
                for(RotateGroupShopEntity rotateGroupShopEntity:rotateGroupShopEntitys){
                    producer.send(getMessage("insert", rotateGroupShopEntity.getRotateGroupID()));
                }
            }
        }catch(Exception ex){
            logger.error(ex.getMessage());
        }

    }

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*))&&args(daoMethod)")
    public void doAfterUpdate(DAOMethod daoMethod){
        try{
            if(daoMethod.getName().equals("restoreRotateGroupShopByShopId")){
                Integer shopId = (Integer)daoMethod.getParams().get("shopId");
                List<RotateGroupShopEntity> rotateGroupShopEntities = rotateGroupShopDAO.queryRotateGroupShopByShopID(shopId);
                for(RotateGroupShopEntity rotateGroupShopEntity:rotateGroupShopEntities){
                    producer.send(getMessage("update",rotateGroupShopEntity.getRotateGroupID()));
                }
            }
            if(daoMethod.getName().equals("updateRotateGroupShop")){
                RotateGroupShopEntity rotateGroupShopEntity = (RotateGroupShopEntity)daoMethod.getParams().get("rotateGroupShop");
                producer.send(getMessage("update",rotateGroupShopEntity.getRotateGroupID()));
            }
            if(daoMethod.getName().equals("updateRotateGroupShopByShopID")){
                Integer shopId = (Integer)daoMethod.getParams().get("shopID");
                List<RotateGroupShopEntity> rotateGroupShopEntities = rotateGroupShopDAO.queryRotateGroupShopByShopID(shopId);
                for(RotateGroupShopEntity rotateGroupShopEntity:rotateGroupShopEntities){
                    producer.send(getMessage("update",rotateGroupShopEntity.getRotateGroupID()));
                }
            }
            if(daoMethod.getName().equals("updateRotateGroupShopRotateGroupIDBatch")){
                Integer rotateGroupId = (Integer)daoMethod.getParams().get("rotateGroupID");
                producer.send(getMessage("update",rotateGroupId));
            }
            if(daoMethod.getName().equals("updateRotateGroupShopByShopIDAndBizID")){
                RotateGroupShopEntity rotateGroupShopEntity = (RotateGroupShopEntity)daoMethod.getParams().get("rotateGroup");
                producer.send(getMessage("update",rotateGroupShopEntity.getRotateGroupID()));
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }

    }

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*))&&args(daoMethod)")
    public void doAfterDelete(DAOMethod daoMethod){
        try{
            if(daoMethod.getName().equals("deleteRotateGroupShop")){
                /*
                Integer id = (Integer)daoMethod.getParams().get("id");
                RotateGroupShopEntity rotateGroupShopEntity = rotateGroupShopDAO.queryRotateGroupShop(id);
                producer.send(getMessage("delete",rotateGroupShopEntity.getRotateGroupID()));
                */
            }
            if(daoMethod.getName().equals("deleteRotateGroupShopByRotateGroupID")){
                Integer rotateGroupId = (Integer)daoMethod.getParams().get("rotateGroupID");
                producer.send(getMessage("delete",rotateGroupId));
            }
            if(daoMethod.getName().equals("deleteRotateGroupShopByShopId")){
                //已经删除，再根据ShopID无法获取RotateGroupID相关信息
            }
            if(daoMethod.getName().equals("deleteRotateGroupShopDirectlyByShopId")){

            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }

    }

    private String getMessage(String type,Integer rotateGroupId){
        return "{\"type\":\""+type+"\",\"rotateGroupId\":"+rotateGroupId+"}";
    }

}
