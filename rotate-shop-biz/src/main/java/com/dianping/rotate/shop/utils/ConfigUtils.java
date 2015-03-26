package com.dianping.rotate.shop.utils;

import com.dianping.combiz.spring.util.PropertiesLoaderSupportUtils;

/**
 * User: zhenwei.wang
 * Date: 15-1-22
 */
public class ConfigUtils {

	public static boolean getSyncRotateGroupTypeTaskTrigger(){
		return PropertiesLoaderSupportUtils.getBoolProperty("rotate-shop-integration-job.syncRotateGroupTypeTaskTrigger", true);
	}

	public static boolean getSyncShopExtendTypeTaskTrigger() {
		return PropertiesLoaderSupportUtils.getBoolProperty("rotate-shop-integration-job.syncExtendShopTypeTaskTrigger", true);
	}

	public static String getIntegrationJobAddress() {
		return PropertiesLoaderSupportUtils.getProperty("rotate-shop-integration-job.address", null);
	}

	public static boolean getBusinessDataProcessorTaskTrigger() {
		return PropertiesLoaderSupportUtils.getBoolProperty("rotate-shop-integration-job.apolloShopBusinessDataProcessorTask.trigger", true);
	}

	public static boolean getRatingDataProcessorTaskTrigger() {
		return PropertiesLoaderSupportUtils.getBoolProperty("rotate-shop-integration-job.apolloShopRatingDataProcessorTask.trigger", true);
	}

	public static boolean getMessageQueueHistoryDataTaskTrigger() {
		return PropertiesLoaderSupportUtils.getBoolProperty("rotate-shop-integration-job.messageQueueHistoryDataTask.trigger", true);
	}

}
