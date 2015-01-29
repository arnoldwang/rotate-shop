package com.dianping.rotate.shop.task;

import com.beust.jcommander.internal.Lists;
import com.dianping.rotate.shop.service.ShopService;
import com.dianping.rotate.shop.utils.Beans;
import com.dianping.rotate.shop.utils.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * User: zhenwei.wang
 * Date: 15-1-27
 */
public class SyncShopExtendTypeTask {

	Logger logger = LoggerFactory.getLogger(SyncShopExtendTypeTask.class);

	private final static int THREAD_NUM = 10;

	@Autowired
	ShopService shopService;

	public void go(){
		if (!ConfigUtils.getSyncShopExtendTypeTaskTrigger()) {
			logger.info("SyncShopExtendTypeTask will not run!");
			return;
		}

		int rotateGroupNum = shopService.getRotateGroupNum();
		int threadPage = rotateGroupNum / THREAD_NUM;
		ExecutorService exe = Executors.newFixedThreadPool(THREAD_NUM);
		List<Future> futureList = Lists.newArrayList();

		logger.info("SyncShopExtendTypeTask.running...");
		long beginTime = System.currentTimeMillis();

		for(int i = 0; i < THREAD_NUM; i++){
			Runnable r = new SyncShopExtendTypeWorkThread(i * threadPage, (i + 1) * threadPage);
			Beans.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(r);
			futureList.add(exe.submit(r));
		}

		for(int i = 0; i < THREAD_NUM; i++){
			try {
				futureList.get(0).get();
			} catch (InterruptedException e) {
				logger.warn("This thread: " + Thread.currentThread().getName() + " is interrupted!", e);
			} catch (ExecutionException e) {
				logger.warn("This thread: " + Thread.currentThread().getName() + " is failed!", e);
			}
		}

		exe.shutdown();

		long endTime = System.currentTimeMillis();
		logger.info("SyncShopExtendTypeTask.end");
		long useTime = (endTime - beginTime) / 1000;
		logger.info("This task use " + useTime / 3600 + " H " + useTime % 3600 / 60 + " m " + useTime % 3600 % 60 + " s!");

	}
}
