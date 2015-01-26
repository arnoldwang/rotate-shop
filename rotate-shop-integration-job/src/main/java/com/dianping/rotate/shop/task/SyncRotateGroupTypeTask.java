package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.utils.ConfigUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: zhenwei.wang
 * Date: 15-1-22
 */
public class SyncRotateGroupTypeTask {

	Logger logger = LoggerFactory.getLogger(SyncRotateGroupTypeTask.class);

	private final static int PAGE_SIZE = 10000;
	private final static int OFFSET = 0;

	@Autowired
	ShopService shopService;


	public void go() {
		if (!ConfigUtils.getSyncRotateGroupTypeTaskTrigger()) {
			logger.info("SyncRotateGroupTypeTask will not run!");
			return;
		}

		logger.info("SyncRotateGroupTypeTask.running...");
		System.out.println("SyncRotateGroupTypeTask.running...");
		long beginTime = System.currentTimeMillis();

		syncRotateGroupType();

		long endTime = System.currentTimeMillis();
		logger.info("SyncRotateGroupTypeTask.end");
		System.out.println("SyncRotateGroupTypeTask.end");
		long useTime = (endTime - beginTime) / 1000;
		logger.info("This task use " + useTime / 3600 + " H " + useTime % 3600 / 60 + " m " + useTime % (3600 * 60) + " s!");
		System.out.println("This task use " + useTime / 3600 + " H " + useTime % 3600 / 60 + " m " + useTime % (3600 * 60) + " s!");
	}

	private void syncRotateGroupType() {
		int endFlag = 0, exceptionFlag = 0;
		int page = PAGE_SIZE;
		int index = OFFSET;
		while (endFlag < 10 && exceptionFlag < 100) {
			try {
				List<Integer> rotateGroupIDList = shopService.getRotateGroupIDList(page, index);
				if (CollectionUtils.isEmpty(rotateGroupIDList)) {
					endFlag++;
					logger.info("rotateGroupID from " + index + " to " + (index + page) + " is empty!");
					continue;
				}

				for (int rotateGroupID : rotateGroupIDList) {
					shopService.updateRotateGroupTypeAndStatus(rotateGroupID);
				}

				logger.info("sync date from " + rotateGroupIDList.get(0) + " to " + (rotateGroupIDList.get(0) + page));
				System.out.println("sync date from " + rotateGroupIDList.get(0) + " to " + (rotateGroupIDList.get(0) + page));
				index += page;
			} catch (Exception e) {
				logger.warn("sync data failed!", e);
				exceptionFlag++;
			}
		}
	}
}
