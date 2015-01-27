package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.service.ShopService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-27
 */
public class SyncShopExtendTypeWorkThread implements Runnable {
	Logger logger = LoggerFactory.getLogger(SyncShopExtendTypeWorkThread.class);

	private final static int PAGE_SIZE = 10000;

	private int threadBegin;
	private int threadEnd;

	@Autowired
	ShopService shopService;

	public SyncShopExtendTypeWorkThread(int threadBegin, int threadEnd){
		this.threadBegin = threadBegin;
		this.threadEnd = threadEnd;
	}

	@Override
	public void run() {
		syncShopExtendType(threadBegin, threadEnd);
	}

	private void syncShopExtendType(int threadBegin, int threadEnd) {
		//todo
		int exceptionFlag = 0;
		int page = PAGE_SIZE;
		int index = threadBegin;
		while (index < (threadEnd + page) && exceptionFlag < 100) {
			try {
				List<Integer> rotateGroupIDList = shopService.getRotateGroupIDList(page, index);
				if (CollectionUtils.isEmpty(rotateGroupIDList)) {
					logger.info("This thread: " + Thread.currentThread().getName() + " rotateGroupID from " + index + " to " + (index + page) + " is empty!");
					index += page;
					continue;
				}

				for(int rotateGroupID: rotateGroupIDList){

				}
			}catch (Exception e){
				logger.warn("This thread: " + Thread.currentThread().getName() + " sync data failed!", e);
				exceptionFlag++;
			}
		}
	}
}
