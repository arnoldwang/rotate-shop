package com.dianping.rotate.shop.api;

import com.dianping.rotate.shop.dto.ApolloShopDTO;

import java.util.List;

/**
 * Created by luoming on 15/1/6.
 */
public interface ApolloShopService {

    /**
     * 获取阿波罗门店信息
     * @param shopID
     * @param bizID
     * @return
     */
    public ApolloShopDTO getApolloShop(int shopID, int bizID);

    /**
     * 批量获取阿波罗门店信息（最大限制一次获取量为5000）
     * @param shopIDList
     * @param bizID
     * @return
     */
    public List<ApolloShopDTO> getApolloShop(List<Integer> shopIDList, int bizID);

	/**
	 * 通过轮转组ID获取门店,不包含扩展信息
	 * @param rotateGroupID
	 * @return 门店列表
	 */
	public List<ApolloShopDTO> getShopByRotateGroupID(int rotateGroupID);
    /**
     * 通过shopId关闭门店
     * @param shopId
     * @return void
     */
    public void deleteApolloShopByShopID(int shopId);
    /**
     * 通过shopId恢复门店的状态
     * @param shopId
     * @return void
     */
    public void restoreApolloShopByShopID(int shopId);
    /**
     * 通过shopId恢复门店的状态
     * @param shopIds
     * @param type
     * @param bizID
     * @return void
     */
    public boolean updateApolloShopTypeByShopIDAndBizID(List<Integer> shopIds,int bizID,int type);

}
