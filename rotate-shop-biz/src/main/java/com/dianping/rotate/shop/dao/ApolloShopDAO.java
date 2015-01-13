package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.core.type.PageModel;
import com.dianping.rotate.shop.entity.ApolloShopEntity;

import java.util.List;

/**
 * Created by luoming on 15/1/4.
 */
public interface ApolloShopDAO extends GenericDao {

    @DAOAction(action = DAOActionType.QUERY)
    List<ApolloShopEntity> queryApolloShopByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.INSERT)
    int addApolloShop(@DAOParam("apolloShop") ApolloShopEntity apolloShop);

    @DAOAction(action = DAOActionType.UPDATE)
    void deleteApolloShopByShopID(@DAOParam("shopID") int shopID);

    @DAOAction(action = DAOActionType.UPDATE)
    void restoreApolloShopByShopID(@DAOParam("shopID") int shopID);

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

}
