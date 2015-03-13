package com.dianping.rotate.shop.dto;

/**
 * Created by sharon on 15-3-12.
 */
public class AdApolloShopRatingDTO {
    private Integer shopid;
    private Integer type;
    private Integer bizId;
    private Integer rating;

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
