package com.dianping.rotate.shop.enums;

/**
 * 战区门店属性映射
 * Created by dev_wzhang on 15-1-8.
 */
public enum TerritoryShopPropertyMapper {

    /**
     * 城市id
     */
    CityID("cityID"),

    /**
     * 门店类型,如大客户等
     */
    ShopType("shopType"),

    /**
     * 行政区
     */
    District("district");

    TerritoryShopPropertyMapper(String territoryProperty) {
        this.territoryProperty = territoryProperty;
    }

    private String territoryProperty;

    public String getTerritoryProperty() {
        return territoryProperty;
    }
}
