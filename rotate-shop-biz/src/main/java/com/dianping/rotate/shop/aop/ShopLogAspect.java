package com.dianping.rotate.shop.aop;

import com.dianping.avatar.dao.DAOMethod;
import com.dianping.rotate.core.api.dto.ShopLogDTO;
import com.dianping.rotate.core.api.enums.SourceEnum;
import com.dianping.rotate.core.api.service.ShopLogService;
import com.dianping.rotate.shop.constants.ShopLogTypeEnum;
import com.dianping.rotate.shop.dao.*;
import com.dianping.rotate.shop.json.*;
import com.dianping.rotate.shop.model.ShopLogEntity;
import com.dianping.rotate.shop.utils.JsonUtil;
import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 现在只对前台操作Shop的动作打log，门店系统的Job校验的内容不记入log
 * 对于ShopLogDTO，有一下几种情况：
 * 1.对门店的操作，没有bizId，没有rotateGroupId, new(old)Value中没有ApolloShopExtend和RotateGroupList
 * 2.对于门店扩展的操作，有bizId，没有rotateGroupId，new(old)Value中只包括ApolloShopExtend
 * 3.对于轮转组的操作，有bizId、rotateGroupId，new(old)Value中只包括RotateGroupList
 * 4.对于所有新增操作，oldValue中相应的属性为null
 * <p/>
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
	RotateGroupShopDAO rotateGroupShopDAO;

	@Autowired
	ShopLogService shopLogService;

	@Pointcut(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeInsert(*)) " +
			"&& args(daoMethod)", argNames = "daoMethod")
	public void insert(DAOMethod daoMethod) {
	}

	@Pointcut(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*)) " +
			"&& args(daoMethod)", argNames = "daoMethod")
	public void update(DAOMethod daoMethod) {
	}


	/**
	 * 新增操作只记录ApolloShop和ApolloShopExtend的log
	 *
	 * @param joinPoint
	 * @param daoMethod
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@Around(value = "insert(daoMethod)", argNames = "joinPoint, daoMethod")
	public Object createInsertLog(ProceedingJoinPoint joinPoint, DAOMethod daoMethod) {
		Object result = null;
		Map<String, Object> params = daoMethod.getParams();
		if (params.containsKey("apolloShop"))
			return createAddShopLog(joinPoint, (ApolloShopEntity) params.get("apolloShop"));
		if (params.containsKey("extendEntities"))
			return createAddApolloShopExtendLog(joinPoint, (List<ApolloShopExtendEntity>) params.get("extendEntities"));
		try {
			result = joinPoint.proceed();
		} catch (Throwable t) {
			logger.warn("execute insert function failed!", t);
		}
		return result;

	}

	/**
	 * 更新操作记录ApolloShop、ApolloShopExtend和RotateGroup的log，且只记录前台操作记录，不记录门店系统Job的操作
	 * 例外：SyncRotateGroupType这个Job的操作和log下来，因为其对RotateGroup的更新比较准确，没有无用操作
	 *
	 * @param joinPoint
	 * @param daoMethod
	 * @return
	 */
	@Around(value = "update(daoMethod)", argNames = "joinPoint, daoMethod")
	public Object createUpdateLog(ProceedingJoinPoint joinPoint, DAOMethod daoMethod) {
		Object result = null;
		Map<String, Object> params = daoMethod.getParams();
		if (params.containsKey("apolloShop"))
			return createUpdateShopLog(joinPoint, (ApolloShopEntity) params.get("apolloShop"));
		if (params.containsKey("shopID")) {
			if (daoMethod.getName().equals("deleteApolloShopByShopID"))
				return createCloseShopLog(joinPoint, (Integer) params.get("shopID"));
			if (daoMethod.getName().equals("restoreApolloShopByShopID"))
				return createOpenShopLog(joinPoint, (Integer) params.get("shopID"));
		}
		if (params.containsKey("apolloShopExtend"))
			return createUpdateShopExtendLog(joinPoint, (ApolloShopExtendEntity) params.get("apolloShopExtend"));
		if (params.containsKey("rotateGroup"))
			return createUpdateRotateGroupLog(joinPoint, (RotateGroupEntity) params.get("rotateGroup"));
		try {
			result = joinPoint.proceed();
		} catch (Throwable t) {
			logger.error("execute update function failed!", t);
		}
		return result;
	}

	private Object createAddShopLog(ProceedingJoinPoint joinPoint, ApolloShopEntity apolloShop) {
		try {
			ShopLogEntity oldValue = new ShopLogEntity();

			int shopId = (Integer) joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(apolloShop.getShopID());
			int type = ShopLogTypeEnum.INSERT_SHOP.getCode();

			createLog(apolloShop.getShopID(), type, null, null, oldValue, newValue);
			return shopId;
		} catch (Exception e) {
			logger.warn("create add shop log failed!", e);
		} catch (Throwable t) {
			logger.error("execute add shop failed!", t);
		}
		return null;
	}

	private Object createAddApolloShopExtendLog(ProceedingJoinPoint joinPoint, List<ApolloShopExtendEntity> extendEntities) {
		try {
			ShopLogEntity oldValue = new ShopLogEntity();
			oldValue.setApolloShopExtendEntity(null);

			Object result = joinPoint.proceed();

			int shopId = extendEntities.get(0).getShopID();
			for (ApolloShopExtendEntity shopExtendEntity : extendEntities) {
				ShopLogEntity newValue = new ShopLogEntity();
				newValue.setApolloShopExtendEntity(shopExtendEntity);
				int bizId = shopExtendEntity.getBizID();
				int type = ShopLogTypeEnum.INSERT_SHOP_EXTEND.getCode();

				createLog(shopId, type, bizId, null, oldValue, newValue);
			}
			return result;
		} catch (Exception e) {
			logger.warn("create add shopExtend log failed!", e);
		} catch (Throwable t) {
			logger.error("execute add shopExtend failed!", t);
		}
		return null;
	}

	private Object createUpdateShopLog(ProceedingJoinPoint joinPoint, ApolloShopEntity apolloShop) {
		try {
			ShopLogEntity oldValue = createShopLogEntity(apolloShop.getShopID());

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(apolloShop.getShopID());
			int type = ShopLogTypeEnum.UPDATE_SHOP.getCode();

			createLog(apolloShop.getShopID(), type, null, null, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create update shop log failed!", e);
		} catch (Throwable t) {
			logger.error("execute update shop failed!", t);
		}
		return null;
	}

	private Object createUpdateRotateGroupLog(ProceedingJoinPoint joinPoint, RotateGroupEntity rotateGroup) {
		try {
			ShopLogEntity oldValue = new ShopLogEntity();
			RotateGroupEntity oldRotateGroup = rotateGroupDAO.getRotateGroup(rotateGroup.getId());
			oldValue.setRotateGroupEntities(Lists.newArrayList(oldRotateGroup));

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = new ShopLogEntity();
			newValue.setRotateGroupEntities(Lists.newArrayList(rotateGroup));
			int bizId = rotateGroup.getBizID();
			int type = ShopLogTypeEnum.INSERT_SHOP_EXTEND.getCode();
			int rotateGroupId = rotateGroup.getId();
			List<RotateGroupShopEntity> rotateGroupShopEntities = rotateGroupShopDAO.queryRotateGroupShopByRotateGroupID(rotateGroupId);
			for (RotateGroupShopEntity rotateGroupShopEntity : rotateGroupShopEntities)
				createLog(rotateGroupShopEntity.getShopID(), type, bizId, rotateGroupId, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create update rotateGroup log failed!", e);
		} catch (Throwable t) {
			logger.error("execute update rotateGroup failed!", t);
		}
		return null;
	}


	private Object createCloseShopLog(ProceedingJoinPoint joinPoint, int shopId) {
		try {
			ShopLogEntity oldValue = createShopLogEntity(shopId);

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(shopId);
			int type = ShopLogTypeEnum.MERGE_SHOP.getCode();

			createLog(shopId, type, null, null, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create close shop log failed!", e);
		} catch (Throwable t) {
			logger.error("execute close shop failed!", t);
		}
		return null;
	}

	private Object createOpenShopLog(ProceedingJoinPoint joinPoint, int shopId) {
		try {
			ShopLogEntity oldValue = createShopLogEntity(shopId);

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(shopId);
			int type = ShopLogTypeEnum.SPLIT_SHOP.getCode();

			createLog(shopId, type, null, null, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create open shop log failed!", e);
		} catch (Throwable t) {
			logger.error("execute open shop failed!", t);
		}
		return null;
	}

	private Object createUpdateShopExtendLog(ProceedingJoinPoint joinPoint, ApolloShopExtendEntity apolloShopExtend) {
		try {
			int shopId = apolloShopExtend.getShopID();
			int bizId = apolloShopExtend.getBizID();
			int type = ShopLogTypeEnum.UPDATE_SHOP_EXTEND.getCode();

			ShopLogEntity oldValue = createShopLogEntity(shopId);
			oldValue.setApolloShopExtendEntity(apolloShopExtendDAO.queryApolloShopExtendByShopIDAndBizID(shopId, bizId).get(0));

			Object result = joinPoint.proceed();

			ShopLogEntity newValue = createShopLogEntity(shopId);
			newValue.setApolloShopExtendEntity(apolloShopExtend);

			createLog(shopId, type, bizId, null, oldValue, newValue);
			return result;
		} catch (Exception e) {
			logger.warn("create update shopExtend log failed!", e);
		} catch (Throwable t) {
			logger.error("execute update shopExtend failed!", t);
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

	private void createLog(int shopId, int type, Integer bizId, Integer rotateGroupId, ShopLogEntity oldValue, ShopLogEntity newValue) throws Exception {
		ShopLogDTO shopLogDTO = ShopLogDTO.builder().build();
		shopLogDTO.setShopId(shopId);
		if (bizId != null)
			shopLogDTO.setBizId(bizId);
		if (rotateGroupId != null)
			shopLogDTO.setRotateGroupId(rotateGroupId);
		shopLogDTO.setType(type);
		shopLogDTO.setSubType(type);
		shopLogDTO.setOldValues(JsonUtil.toStr(oldValue));
		shopLogDTO.setNewValues(JsonUtil.toStr(newValue));
		shopLogDTO.setSource(SourceEnum.ROTATE_SHOP.getCode());
		shopLogService.insert(shopLogDTO);
	}
}
