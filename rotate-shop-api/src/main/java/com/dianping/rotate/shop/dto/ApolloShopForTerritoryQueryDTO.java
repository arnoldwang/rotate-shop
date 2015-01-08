package com.dianping.rotate.shop.dto;

/**
 * 提供给战区使用的查询门店dto
 * Created by dev_wzhang on 15-1-8.
 */
public class ApolloShopForTerritoryQueryDTO {

    /**
     * 战区规则
     */
    private String territoryRule;

    /**
     * 聚合业务id
     */
    private Integer bizId;

    /**
     * 模运算结果
     */
    private Integer modValue;

    /**
     * 模运算的基数
     */
    private Integer modKey;

    /**
     * 分页大小
     */
    private Integer pageSize ;

    /**
     * 分页索引
     */
    private Integer pageIndex;

    public String getTerritoryRule() {
        return territoryRule;
    }

    public void setTerritoryRule(String territoryRule) {
        this.territoryRule = territoryRule;
    }

    public Integer getBizId() {
        return modValue;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Integer getModValue() {
        return modValue;
    }

    public void setModValue(Integer modValue) {
        this.modValue = modValue;
    }

    public Integer getModKey() {
        return modKey;
    }

    public void setModKey(Integer modKey) {
        this.modKey = modKey;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
