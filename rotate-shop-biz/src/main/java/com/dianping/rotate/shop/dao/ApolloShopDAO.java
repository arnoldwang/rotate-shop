package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.core.type.PageModel;
import com.dianping.rotate.shop.json.ApolloShopEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ApolloShopDAO extends GenericDao {

	/**
	 * 根据shopID查询门店
	 * @param shopID
	 * @return 只返回状态为正常的门店
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopEntity> queryApolloShopByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 根据shopID查询门店
	 * @param shopId
	 * @return 返回所有状态门店
	 */
	@DAOAction(action = DAOActionType.LOAD)
	ApolloShopEntity queryApolloShopByShopIDWithNoStatus(@DAOParam("shopID") int shopId);

	/**
	 * 根据shopID列表查询门店
	 * @param shopIDList
	 * @return 返回所有状态门店列表
	 */
    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopEntity> queryApolloShopByShopIDList(@DAOParam("shopIDList") List<Integer> shopIDList);

	/**
	 * 插入一个新门店
	 * @param apolloShop
	 * @return 新门店的ID
	 */
    @DAOAction(action = DAOActionType.INSERT)
    int addApolloShop(@DAOParam("apolloShop") ApolloShopEntity apolloShop);

	/**
	 * 根据shopID关闭门店
	 * @param shopID
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void deleteApolloShopByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 根据shopID重新开放门店
	 * @param shopID
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void restoreApolloShopByShopID(@DAOParam("shopID") int shopID);

	/**
	 * 更新门店信息
	 * @param apolloShop
	 */
    @DAOAction(action = DAOActionType.UPDATE)
    void updateApolloShop(@DAOParam("apolloShop") ApolloShopEntity apolloShop);


    /**
     * 提供给战区使用的，根据给定条件分页查询门店数据
     * @param ruleExpression:给定的rule表达式,必须符合sql语法
     * @param modKey:模运算的基数
     * @param modValue:模运算的结果值
     * @param max:行数
     * @param page:页索引
     * @return PageModel:符合条件的分页数据集
     */
    @DAOAction(action = DAOActionType.PAGE)
    PageModel queryApolloShopsForTerritory(@DAOParam("ruleExpression") String ruleExpression
            ,@DAOParam("bizID") int bizID
            ,@DAOParam("modKey") int modKey
            ,@DAOParam("modValue") int modValue
            ,@DAOParam("max") int max
            ,@DAOParam("page") int page);

    /**
     * 提供给战区使用的，根据给定条件分页查询门店ID
     * @param ruleExpression:规则表达式
     * @param startIndex：起始索引
     * @param limitSize:结束索引
     * @return
     */
    @DAOAction(action = DAOActionType.QUERY)
    List<Integer> queryApolloShopIDForTerritory(
            @DAOParam("ruleExpression") String ruleExpression
            ,@DAOParam("startIndex") int startIndex
            ,@DAOParam("limitSize") int limitSize);

}
