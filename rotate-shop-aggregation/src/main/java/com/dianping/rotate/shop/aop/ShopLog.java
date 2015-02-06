package com.dianping.rotate.shop.aop;

import com.dianping.rotate.shop.dao.ApolloShopDAO;
import com.dianping.rotate.shop.json.ApolloShopEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: zhenwei.wang
 * Date: 15-2-6
 */
@Aspect
public class ShopLog {

	@Autowired
	ApolloShopDAO apolloShopDAO;

	@Pointcut("execution(* com.dianping.rotate.shop.service.impl.ShopServiceImpl.closeShop(..)) && args(shopId)")
	public void closeApolloShop(int shopId){

	}

	@Around("closeApolloShop(shopId)")
	public void createShopLog(ProceedingJoinPoint joinPoint, int shopId){
		ApolloShopEntity oldShop = apolloShopDAO.queryApolloShopByShopID(shopId).get(0);
	}
}
