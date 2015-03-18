package com.dianping.rotate.shop.aop;

import com.dianping.avatar.dao.DAOMethod;
import com.dianping.rotate.shop.json.ApolloShopEntity;
import com.dianping.rotate.shop.producer.ApolloShopMessageProducer;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zaza on 15/3/16.
 */
@Aspect
@Component
public class ApolloShopMessageAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("apolloShopMessageProducer")
    ApolloShopMessageProducer producer;
    /*
    ApolloShop和ApolloShopExtend的信息插入和发消息可以有两种触发方式：
    一，在每次接受到Swallow消息处理完后发送（因为ApolloShop的更新入口只会是POI的Swallow消息）
    二，通过AOP监控DAO层，但是这样会带来一个问题，因为ApolloShop关联了一系列子对象，如果只是监控
       某个DAO层，会不会消息发出去了，然后子对象还没有创建成功
    所以，这里只是监控一下标记大客户的变化，其他得还是分散到各个消息处理逻辑里面发消息
    * */

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeInsert(*))&&args(daoMethod)")
    public void doAfterInsert(DAOMethod daoMethod){
        /*
        if(daoMethod.getName().equals("addApolloShop")){
            try{
                Integer shopId = ((ApolloShopEntity)daoMethod.getParams().get("apolloShop")).getShopID();
                producer.send(getMessage(shopId,"insert"));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        */
    }
/*
    大部分的ApolloShop的附属信息的更新都是由Shop触发的，所以只要监控ApolloShop的Update
    而且所有的数据更新的处理方式都是一致的，ApolloShopExtend更新入口也是ApolloShop，除非批量标记大客户
    例外的情况就是批量标记大客户，是运营后台触发的，所以要单独监控
* */
    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*))&&args(daoMethod)")
    public void doAfterUpdate(DAOMethod daoMethod){
        /*
        if(daoMethod.getName().equals("updateApolloShop")){
            try{
                Integer shopId = ((ApolloShopEntity)daoMethod.getParams().get("apolloShop")).getShopID();
                producer.send(getMessage(shopId,"update"));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        if(daoMethod.getName().equals("updateApolloShopExtend")){
            try{
                Integer shopId = ((ApolloShopEntity)daoMethod.getParams().get("apolloShopExtend")).getShopID();
                producer.send(getMessage(shopId,"update"));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        */
        //监控大客户标记
        if(daoMethod.getName().equals("updateApolloShopExtendTypeByShopIDListAndType")){
            try{
                List<Integer> shopIds = (List<Integer>)daoMethod.getParams().get("shopIDList");
                Integer bizId = (Integer)daoMethod.getParams().get("bizID");
                for(Integer shopId:shopIds){
                    producer.send(getMessage(shopId,"update",bizId));
                }
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
    }

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*))&&args(daoMethod)")
    public void doAfterDelete(DAOMethod daoMethod){

    }

    private String getMessage(Integer shopId,String type,Integer bizId){
        return "{\"shopId\":"+shopId+",\"type\":"+type+",\"bizId\":"+bizId+"}";
    }
}
