package com.dianping.rotate.shop.aop;

import com.dianping.rotate.core.api.dto.ShopLogDTO;
import com.dianping.rotate.core.api.enums.SourceEnum;
import com.dianping.rotate.core.api.service.ShopLogService;
import com.dianping.rotate.shop.constants.ShopLogTypeEnum;
import com.dianping.rotate.shop.dao.*;
import com.dianping.rotate.shop.json.*;
import com.dianping.rotate.shop.model.ShopLogEntity;
import com.dianping.rotate.shop.utils.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 现在只对前台操作Shop的动作打log，门店系统的Job校验的内容不记入log
 * User: zhenwei.wang
 * Date: 15-2-6
 */
@Aspect
@Component
public class ShopLogAspect {

	Logger logger = LoggerFactory.getLogger(ShopLogAspect.class);

	@Autowired
	ApolloShopDAO apolloShopDAO;

	@Autowired
	ApolloShopExtendDAO apolloShopExtendDAO;

	@Autowired
	ShopRegionDAO shopRegionDAO;

	@Autowired
	ShopCategoryDAO shopCategoryDAO;

	@Autowired
	RotateGroupDAO rotateGroupDAO;

	@Autowired
	ShopLogService shopLogService;

	@Pointcut(value = "execution(* com.dianping.rotate.shop.service.impl.ShopServiceImpl.addShop(int)) && args(shopId)", argNames = "shopId")
	public void addApolloShop(int shopId) {
	}

	@Pointcut(value = "execution(* com.dianping.rotate.shop.service.impl.ShopServiceImpl.closeShop(int)) && args(shopId)", argNames = "shopId")
	public void closeApolloShop(int shopId) {
	}

	@Pointcut(value = "execution(* com.dianping.rotate.shop.service.impl.ShopServiceImpl.openShop(int)) && args(shopId)", argNames = "shopId")
	public void openApolloShop(int shopId) {
	}

	@Pointcut(value = "execution(* com.dianping.rotate.shop.service.impl.ShopServiceImpl.updateShop(int) ) && args(shopId)", argNames = "shopId")
	public void updateApolloShop(int shopId) {
	}

	@Pointcut(value = "execution(* com.dianping.rotate.shop.service.impl.ShopServiceImpl.insertApolloShopExtendList(int)) && args(shopId))", argNames = "shopId")
	public void addApolloShopExtend(int shopId){
	}

	@Pointcut(value = "execution(* com.dianping.rotate.shop.service.impl.ShopServiceImpl.insertRotateGroup(ApolloShopExtendEntity)) && args(apolloShopExtend)", argNames = "apolloShopExtend")
	public void addRotateGroup(ApolloShopExtendEntity apolloShopExtendEntity){
	}

	@Around(value = "addApolloShop(shopId)", argNames = "joinPoint, shopId")
	public Object createAddShopLog(ProceedingJoinPoint joinPoint, int shopId) {
		try {
			ShopLogEntity oldValue = createShopLogEntity(shopId);

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(shopId);
			int type = ShopLogTypeEnum.INSERT_SHOP.getCode();

			createLog(shopId, type, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create add shop log failed!", e);
		} catch (Throwable t) {
			logger.error("execute add shop failed!", t);
		}
		return null;
	}

	@Around(value = "closeApolloShop(shopId)", argNames = "joinPoint, shopId")
	public Object createCloseShopLog(ProceedingJoinPoint joinPoint, int shopId) {
		try {
			ShopLogEntity oldValue = createShopLogEntity(shopId);

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(shopId);
			int type = ShopLogTypeEnum.MERGE_SHOP.getCode();

			createLog(shopId, type, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create close shop log failed!", e);
		} catch (Throwable t) {
			logger.error("execute close shop failed!", t);
		}
		return null;
	}

	@Around(value = "openApolloShop(shopId)", argNames = "joinPoint, shopId")
	public Object createOpenShopLog(ProceedingJoinPoint joinPoint, int shopId) {
		try {
			ShopLogEntity oldValue = createShopLogEntity(shopId);

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(shopId);
			int type = ShopLogTypeEnum.SPLIT_SHOP.getCode();

			createLog(shopId, type, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create open shop log failed!", e);
		} catch (Throwable t) {
			logger.error("execute open shop failed!", t);
		}
		return null;
	}

	@Around(value = "updateApolloShop(shopId)", argNames = "joinPoint, shopId")
	public Object createUpdateShopLog(ProceedingJoinPoint joinPoint, int shopId) {
		try {
			ShopLogEntity oldValue = createShopLogEntity(shopId);

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(shopId);
			int type = ShopLogTypeEnum.UPDATE_SHOP.getCode();

			createLog(shopId, type, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create update shop log failed!", e);
		} catch (Throwable t) {
			logger.error("execute update shop failed!", t);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Around(value = "addApolloShopExtend(shopId)", argNames = "joinPoint, shopId")
	public Object createAddApolloShopExtendLog(ProceedingJoinPoint joinPoint, int shopId){
		try {
			ShopLogEntity oldValue = new ShopLogEntity();
			oldValue.setApolloShopExtendEntity(null);

			List<ApolloShopExtendEntity> result =(List<ApolloShopExtendEntity>) joinPoint.proceed();

			for (ApolloShopExtendEntity shopExtendEntity: result){
				ShopLogEntity newValue = new ShopLogEntity();
				int bizId = shopExtendEntity.getBizID();
				int type = ShopLogTypeEnum.INSERT_SHOP_EXTEND.getCode();

				createLog(shopId, type, bizId, oldValue, newValue);
			}
			return result;
		} catch (Exception e) {
			logger.warn("create add shopExtend log failed!", e);
		} catch (Throwable t) {
			logger.error("execute add shopExtend failed!", t);
		}
		return null;
	}

	@Around(value = "addRotateGroup(apolloShopExtend)", argNames = "joinPoint, apolloShopExtend")
	public Object createAddRotateGroupLog(ProceedingJoinPoint joinPoint, ApolloShopExtendEntity apolloShopExtend){
		try {
			ShopLogEntity oldValue = new ShopLogEntity();
			oldValue.setRotateGroupEntities(null);

			int result =(Integer) joinPoint.proceed();

				ShopLogEntity newValue = new ShopLogEntity();

				int bizId = shopExtendEntity.getBizID();
				int type = ShopLogTypeEnum.INSERT_SHOP_EXTEND.getCode();

				createLog(shopId, type, bizId, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create add shopExtend log failed!", e);
		} catch (Throwable t) {
			logger.error("execute add shopExtend failed!", t);
		}
		return null;
	}

	private ShopLogEntity createShopLogEntity(int shopId) {
		ShopLogEntity entity = new ShopLogEntity();
		ApolloShopEntity apolloShop = apolloShopDAO.queryApolloShopByShopIDWithNoStatus(shopId);
		List<ShopRegionEntity> shopRegionEntities = shopRegionDAO.queryShopRegionByShopID(shopId);
		List<ShopCategoryEntity> shopCategoryEntities = shopCategoryDAO.queryShopCategoryByShopID(shopId);
		List<RotateGroupEntity> rotateGroupEntities = rotateGroupDAO.queryRotateGroupByShopID(shopId);
		entity.setApolloShopEntity(apolloShop);
		entity.setShopRegionEntities(shopRegionEntities);
		entity.setShopCategoryEntities(shopCategoryEntities);
		entity.setRotateGroupEntities(rotateGroupEntities);
		return entity;
	}

	private void createLog(int shopId, int type, ShopLogEntity oldValue, ShopLogEntity newValue) throws Exception {
		ShopLogDTO shopLogDTO = ShopLogDTO.builder().build();
		shopLogDTO.setShopId(shopId);
		shopLogDTO.setType(type);
		shopLogDTO.setSubType(type);
		shopLogDTO.setOldValues(JsonUtil.toStr(oldValue));
		shopLogDTO.setNewValues(JsonUtil.toStr(newValue));
		shopLogDTO.setSource(SourceEnum.ROTATE_SHOP.getCode());
		shopLogService.insert(shopLogDTO);
	}

	private void createLog(int shopId, int type, int bizId, ShopLogEntity oldValue, ShopLogEntity newValue) throws Exception{
		ShopLogDTO shopLogDTO = ShopLogDTO.builder().build();
		shopLogDTO.setShopId(shopId);
		shopLogDTO.setBizId(bizId);
		shopLogDTO.setType(type);
		shopLogDTO.setSubType(type);
		shopLogDTO.setOldValues(JsonUtil.toStr(oldValue));
		shopLogDTO.setNewValues(JsonUtil.toStr(newValue));
		shopLogDTO.setSource(SourceEnum.ROTATE_SHOP.getCode());
		shopLogService.insert(shopLogDTO);
	}
}
