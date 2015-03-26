package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.json.RotateGroupEntity;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.utils.ConfigUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-26
 */
public class SyncRotateGroupTypeWorkThread implements Runnable {

	Logger logger = LoggerFactory.getLogger(SyncRotateGroupTypeWorkThread.class);

	private final static int PAGE_SIZE = 10000;

	private int threadBegin;
	private int threadEnd;

	@Autowired
	ShopService shopService;


	public SyncRotateGroupTypeWorkThread(int threadBegin, int threadEnd) {
		this.threadBegin = threadBegin;
		this.threadEnd = threadEnd;
	}

	@Override
	public void run() {
		syncRotateGroupType(threadBegin, threadEnd);
	}

	private void syncRotateGroupType(int threadBegin, int threadEnd) {
		int exceptionFlag = 0;
		int page = PAGE_SIZE;
		int index = threadBegin;
		while (index < (threadEnd + page) && exceptionFlag < 100) {
			try {
				if (!ConfigUtils.getSyncRotateGroupTypeTaskTrigger()) {
					logger.info("SyncRotateGroupTypeTask stop!");
					return;
				}

				List<RotateGroupEntity> rotateGroupList = shopService.getRotateGroupList(page, index);
				if (CollectionUtils.isEmpty(rotateGroupList)) {
					logger.info("This thread: " + Thread.currentThread().getName() + " rotateGroupID from "
							+ index + " to " + (index + page) + " is empty!");
					index += page;
					continue;
				}

				for (RotateGroupEntity rotateGroupEntity : rotateGroupList) {
					shopService.updateRotateGroupType(rotateGroupEntity.getId());
				}

				logger.info("This thread: " + Thread.currentThread().getName() + " sync date from "
						+ rotateGroupList.get(0).getId() + " to " + (rotateGroupList.get(0).getId() + page));
				index += page;
			} catch (Exception e) {
				logger.warn("This thread: " + Thread.currentThread().getName() + " sync data failed!", e);
				exceptionFlag++;
			}
		}
		logger.info("This thread: " + Thread.currentThread().getName() + " End! ");
	}

}
