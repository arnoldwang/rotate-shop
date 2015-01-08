package com.dianping.rotate.shop.factory.impl;

import com.dianping.rotate.shop.constants.BizType;
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
		apolloShopExtendEntity.setType(0);
		apolloShopExtendEntity.setBizID(BizType.JIAOYIPINGTAI);
		apolloShopExtendEntity.setRating("");
		return apolloShopExtendEntity;
	}
}
