package com.dianping.rotate.shop.aop;

import com.dianping.avatar.dao.DAOMethod;
import com.dianping.rotate.shop.producer.ApolloShopMessageProducer;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeInsert(*))&&args(daoMethod)")
    public void doAfterInsert(DAOMethod daoMethod){

    }

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*))&&args(daoMethod)")
    public void doAfterUpdate(DAOMethod daoMethod){

    }

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*))&&args(daoMethod)")
    public void doAfterDelete(DAOMethod daoMethod){

    }
}
