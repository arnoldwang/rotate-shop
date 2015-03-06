package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.RotateGroupShopEntity;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-4
 */
public interface RotateGroupShopDAO extends GenericDao {

	@DAOAction(action = DAOActionType.INSERT)
	public int addToRotateGroupShop(@DAOParam("rotateGroupShop") RotateGroupShopEntity rotateGroupShop);

	@DAOAction(action = DAOActionType.UPDATE)
	public void deleteRotateGroupShop(@DAOParam("id") int id);

	@DAOAction(action = DAOActionType.UPDATE)
	public void deleteRotateGroupShopByRotateGroupID(@DAOParam("rotateGroupID") int rotateGroupID);

	@DAOAction(action = DAOActionType.UPDATE)
	public void deleteRotateGroupShopByShopId(@DAOParam("shopId") int shopId);

	@DAOAction(action = DAOActionType.UPDATE)
	public void restoreRotateGroupShopByShopId(@DAOParam("shopId") int shopId);

	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroupShop(@DAOParam("rotateGroupShop") RotateGroupShopEntity rotateGroupShop);

	@DAOAction(action = DAOActionType.LOAD)
	public RotateGroupShopEntity queryRotateGroupShop(@DAOParam("id") int id);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupShopEntity> queryRotateGroupShopByRotateGroupID(@DAOParam("rotateGroupID") int rotateGroupID);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupShopEntity> queryRotateGroupShopByRotateGroupIDList(@DAOParam("rotateGroupIDList") List<Integer> rotateGroupIDList);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupShopEntity> queryRotateGroupShopByShopID(@DAOParam("shopId") int shopId);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupShopEntity> queryRotateGroupShopByShopGroupIDAndBizID(@DAOParam("shopGroupID") int shopGroupId,
																				 @DAOParam("bizID") int bizID);

	@DAOAction(action = DAOActionType.INSERT)
	public void addToRotateGroupShopByList(@DAOParam("rotateGroupShopList") List<RotateGroupShopEntity> rotateGroupShopEntityList);

	@DAOAction(action = DAOActionType.LOAD)
	public int getShopNumInGroup(@DAOParam("rotateGroupID") int rotateGroupID);

	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroupShopByShopID(@DAOParam("shopID") int shopId, @DAOParam("shopGroupID") int shopGroupId);

	@DAOAction(action = DAOActionType.QUERY)
	public List<Integer> queryShopIDByRotateGroupID(@DAOParam("rotateGroupID") int rotateGroupID);

	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroupShopRotateGroupIDBatch(@DAOParam("rotateGroupID") int rotateGroupID,
														@DAOParam("rotateGroupIDList") List<Integer> rotateGroupIDList);

	@DAOAction(action = DAOActionType.UPDATE)
	public void updateRotateGroupShopByShopIDAndBizID(@DAOParam("rotateGroup") RotateGroupShopEntity rotateGroupShopEntity,
													  @DAOParam("bizID") int bizId);

	@DAOAction(action = DAOActionType.LOAD)
	public RotateGroupShopEntity queryRotateGroupShopByShopIDAndBizID(@DAOParam("shopID") int shopId,
																	  @DAOParam("bizID") int bizId);

	@DAOAction(action = DAOActionType.DELETE)
	public void deleteRotateGroupShopDirectlyByShopId(@DAOParam("shopID") int shopId);

	@DAOAction(action = DAOActionType.QUERY)
	public List<RotateGroupShopEntity> queryRotateGroupShopByShopGroupIDAndBizIDAndCityID(@DAOParam("shopGroupID") int shopGroupId,
																						  @DAOParam("bizID") int bizId,
																						  @DAOParam("cityID") int cityId,
																						  @DAOParam("pageSize") int pageSize,
																						  @DAOParam("offset") int pageIndex);

	@DAOAction(action = DAOActionType.LOAD)
	public int getTotalNumByShopGroupIDAndBizIDAndCityID(@DAOParam("shopGroupID") int shopGroupID,
														 @DAOParam("bizID") int bizID,
														 @DAOParam("cityID") int cityID);
}
