package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.ApolloShopExtendEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ApolloShopExtendDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopExtendEntity> queryApolloShopExtendByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopExtendEntity> queryApolloShopExtendByShopIDAndBizID(@DAOParam("shopID") int shopID, @DAOParam("bizID") int bizID);

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopExtendEntity> queryApolloShopExtendByShopIDListAndBizID(@DAOParam("shopIDList") List<Integer> shopIDList, @DAOParam("bizID") int bizID);

	/**
	 * 插入一个ApolloShopExtend
	 * @param apolloShopExtend
	 * @return
	 */
    @DAOAction(action = DAOActionType.INSERT)
    int addApolloShopExtend(@DAOParam("apolloShopExtend") ApolloShopExtendEntity apolloShopExtend);

	/**
	 * 删除此ID门店所有的shopExtend
	 * @param shopID
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void deleteApolloShopExtendByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 重开此ID门店所有的shopExtend
	 * @param shopID
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void restoreApolloShopExtendByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 按shopID和bizID
	 * 更新ApolloShopExtend信息，不包括status
	 * @param apolloShopExtend
	 * @return
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void updateApolloShopExtend(@DAOParam("apolloShopExtend") ApolloShopExtendEntity apolloShopExtend);

	/**
	 * 通过门店ID查询shopExtend的数量
	 * @param shopId
	 * @return
	 */
	@DAOAction(action = DAOActionType.LOAD)
	int getApolloShopExtendNumByShopID(@DAOParam("shopID") int shopId);

	/**
	 * 批量插入ApolloShopExtend
	 * @param extendEntities
	 */
	@DAOAction(action = DAOActionType.INSERT)
	void addApolloShopExtendByList(@DAOParam("extendEntities") List<ApolloShopExtendEntity> extendEntities);

    @DAOAction(action = DAOActionType.UPDATE)
    int updateApolloShopExtendRating(@DAOParam("apolloShopExtendList") List<ApolloShopExtendEntity> apolloShopExtendList);

	/**
	 * 查询此轮转组下大客户门店的数量
	 * @param rotateGroupID
	 * @return
	 */
	@DAOAction(action = DAOActionType.LOAD)
	int queryVipShopExtendNumByRotateGroupID(@DAOParam("rotateGroupID")int rotateGroupID);

	/**
	 * 更新轮转组下ApolloShopExtend的Type
	 * @param rotateGroupID
	 */
	@DAOAction(action = DAOActionType.UPDATE)
	void updateApolloShopExtendTypeByRotateGroupID(@DAOParam("rotateGroupID")int rotateGroupID);

    /**
     * 更新轮转组下ApolloShopExtend的Type
     * @param rotateGroupIDList
     * @param type
     */
    @DAOAction(action = DAOActionType.UPDATE)
    void updateApolloShopExtendTypeByRotateGroupIDListAndType(@DAOParam("rotateGroupIDList")List<Integer> rotateGroupIDList, @DAOParam("type")int type, @DAOParam("bizID")int bizID);

	/**
	 * 硬删除ApolloShopExtend
	 * @param shopId
	 */
	@DAOAction(action = DAOActionType.DELETE)
	void deleteApolloShopExtendDirectlyByShopID(@DAOParam("shopID") int shopId);

    /**
     * 更新轮转组下ApolloShopExtend的Type
     * @param shopIDList
     * @param type
     * @param bizID
     */
    @DAOAction(action = DAOActionType.UPDATE)
    void updateApolloShopExtendTypeByShopIDListAndType(@DAOParam("shopIDList")List<Integer> shopIDList, @DAOParam("type")int type, @DAOParam("bizID")int bizID);


}
