package com.dianping.rotate.shop.impl;

import com.dianping.avatar.log.AvatarLogger;
import com.dianping.avatar.log.AvatarLoggerFactory;
import com.dianping.core.type.PageModel;
import com.dianping.rotate.shop.api.ApolloShopForTerritoryService;
import com.dianping.rotate.shop.dao.ApolloShopDAO;
import com.dianping.rotate.shop.dto.ApolloShopForTerritoryQueryDTO;


import com.dianping.rotate.shop.enums.TerritoryShopPropertyMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.impl.AvalonLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.QualifierEntry;
import org.springframework.stereotype.Service;

/**
 * 提供给战区使用的门店服务
 *
 * @author wei.zhang.sh@dianping.com
 *         Created on 2015-01-08.
 */

@Service("apolloShopForTerritoryService")
public class ApolloShopForTerritoryServiceImpl implements ApolloShopForTerritoryService {

    @Autowired
    private ApolloShopDAO apolloShopDAO;

    //AvatarLogger
    AvatarLogger avatarLogger = AvatarLoggerFactory.getLogger(ApolloShopForTerritoryServiceImpl.class);

    @Override
    public PageModel batchFetchApolloShop(ApolloShopForTerritoryQueryDTO queryDto) {
        //01.检查入参合法性
        if (queryDto == null
                || queryDto.getBizId() == null
                || StringUtils.isEmpty(queryDto.getTerritoryRule())
                || queryDto.getModKey() == null
                || queryDto.getModValue() == null) {
            String inputParams = null;
            if (queryDto == null) {
                inputParams = "dto为null";
            } else {
                inputParams = String.format("bizId:s%,ruleExperession:s%,modKey:s%,modValue:s%"
                        , queryDto.getBizId(), queryDto.getTerritoryRule(), queryDto.getModKey(), queryDto.getModValue());
            }

            avatarLogger.info("参数错误:"+inputParams);
            throw new RuntimeException("参数错误，请检查传入参数!" + inputParams);
        }

        //记录日志
        avatarLogger.info(String.format("批量获取门店信息参数信息：bizId=s%,territoryRule=s%,modKey=s%,modValue=s%" +
                ",pageSize=s%,pageIndex=s%", queryDto.getBizId(), queryDto.getTerritoryRule()
                , queryDto.getModKey(), queryDto.getModValue(), queryDto.getPageSize(), queryDto.getPageIndex()));

        //02.校验传入战区规则是否符合门店定义
        String territoryRule = replaceTerritoryProperty(queryDto.getTerritoryRule());

        //03.查询符合条件的门店
        PageModel pageResult = apolloShopDAO.queryApolloShopsForTerritory(territoryRule, queryDto.getBizId()
                , queryDto.getModKey(), queryDto.getModValue()
                , queryDto.getPageSize(), queryDto.getPageIndex());

        //返回记录日志
        avatarLogger.info(String.format("返回数据日志，符合条件记录数:s%,当前页索引:s%,共s%页."
                , pageResult.getRecordCount(), pageResult.getPage(), pageResult.getPageCount()));

        return pageResult;
    }

    /**
     * 将战区中不同于门店系统中定义的属性，替换成门店系统中使用的属性
     *
     * @param territoryRule，战区规则
     * @return String:替换后的规则
     */
    private String replaceTerritoryProperty(String territoryRule) {

        String lowerCaseRule = territoryRule.toLowerCase();//先转小写用于比较

        //替换cityID，规则中不包含门店中定义的城市属性，包含战区系统定义的城市属性,则将规则中的城市属性名替换成门店系统中定义的
        if (lowerCaseRule.contains(TerritoryShopPropertyMapper.CityID.getTerritoryProperty().toLowerCase())
                && !lowerCaseRule.contains(TerritoryShopPropertyMapper.CityID.toString().toLowerCase())) {
            territoryRule = territoryRule.replaceAll(TerritoryShopPropertyMapper.CityID.getTerritoryProperty(), TerritoryShopPropertyMapper.CityID.toString());
        }

        //替换District，同上
        if (lowerCaseRule.contains(TerritoryShopPropertyMapper.District.getTerritoryProperty().toLowerCase())
                && !lowerCaseRule.contains(TerritoryShopPropertyMapper.District.toString().toLowerCase())) {
            territoryRule = territoryRule.replaceAll(TerritoryShopPropertyMapper.District.getTerritoryProperty(), TerritoryShopPropertyMapper.District.toString());
        }

        //替换ShopType，同上
        if (!lowerCaseRule.contains(TerritoryShopPropertyMapper.ShopType.toString().toLowerCase())
                && lowerCaseRule.contains(TerritoryShopPropertyMapper.ShopType.toString().toLowerCase())) {
            territoryRule = territoryRule.replaceAll(TerritoryShopPropertyMapper.ShopType.getTerritoryProperty(), TerritoryShopPropertyMapper.ShopType.toString());
        }

        return territoryRule;
    }

}
