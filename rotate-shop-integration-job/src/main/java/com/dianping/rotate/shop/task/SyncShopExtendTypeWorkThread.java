package com.dianping.rotate.shop.task;

/**
 * User: zhenwei.wang
 * Date: 15-1-27
 */
public class SyncShopExtendTypeWorkThread implements Runnable {

	private int threadBegin;
	private int threadEnd;

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
	}
}
