package com.dianping.rotate.shop.model;

import com.dianping.rotate.shop.json.*;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-2-6
 */
public class ShopLogEntity {

	private ApolloShopEntity apolloShopEntity;

	private ApolloShopExtendEntity apolloShopExtendEntity;

	private List<ShopRegionEntity> shopRegionEntities;

	private List<ShopCategoryEntity> shopCategoryEntities;

	private List<RotateGroupEntity> rotateGroupEntities;

	private List<RotateGroupShopEntity> rotateGroupShopEntities;

	public ApolloShopEntity getApolloShopEntity() {
		return apolloShopEntity;
	}

	public void setApolloShopEntity(ApolloShopEntity apolloShopEntity) {
		this.apolloShopEntity = apolloShopEntity;
	}

	public ApolloShopExtendEntity getApolloShopExtendEntity() {
		return apolloShopExtendEntity;
	}

	public void setApolloShopExtendEntity(ApolloShopExtendEntity apolloShopExtendEntity) {
		this.apolloShopExtendEntity = apolloShopExtendEntity;
	}

	public List<ShopRegionEntity> getShopRegionEntities() {
		return shopRegionEntities;
	}

	public void setShopRegionEntities(List<ShopRegionEntity> shopRegionEntities) {
		this.shopRegionEntities = shopRegionEntities;
	}

	public List<ShopCategoryEntity> getShopCategoryEntities() {
		return shopCategoryEntities;
	}

	public void setShopCategoryEntities(List<ShopCategoryEntity> shopCategoryEntities) {
		this.shopCategoryEntities = shopCategoryEntities;
	}

	public List<RotateGroupEntity> getRotateGroupEntities() {
		return rotateGroupEntities;
	}

	public void setRotateGroupEntities(List<RotateGroupEntity> rotateGroupEntities) {
		this.rotateGroupEntities = rotateGroupEntities;
	}

	public List<RotateGroupShopEntity> getRotateGroupShopEntities() {
		return rotateGroupShopEntities;
	}

	public void setRotateGroupShopEntities(List<RotateGroupShopEntity> rotateGroupShopEntities) {
		this.rotateGroupShopEntities = rotateGroupShopEntities;
	}
}
