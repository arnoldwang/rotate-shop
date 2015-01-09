package com.dianping.rotate.shop.factory.impl;

import com.dianping.rotate.shop.constants.ApolloShopTypeEnum;
import com.dianping.rotate.shop.constants.BizTypeEnum;
import com.dianping.rotate.shop.entity.ApolloShopExtendEntity;
import com.dianping.rotate.shop.factory.ApolloShopExtend;

/**
 * User: zhenwei.wang
 * Date: 15-1-7
 */
public class TPApolloShopExtend implements ApolloShopExtend {
	@Override
	public  ApolloShopExtendEntity createApolloShopExtend(int shopID) {
		ApolloShopExtendEntity apolloShopExtendEntity = new ApolloShopExtendEntity();
		apolloShopExtendEntity.setShopID(shopID);
		apolloShopExtendEntity.setType(ApolloShopTypeEnum.COMMON.getCode());
		apolloShopExtendEntity.setBizID(BizTypeEnum.TH_6.getCode());
		apolloShopExtendEntity.setRating("");
		return apolloShopExtendEntity;
	}
}
