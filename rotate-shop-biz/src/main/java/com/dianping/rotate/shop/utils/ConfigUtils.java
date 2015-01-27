package com.dianping.rotate.shop.utils;

import com.dianping.combiz.spring.util.PropertiesLoaderSupportUtils;

/**
 * User: zhenwei.wang
 * Date: 15-1-22
 */
public class ConfigUtils {

	public static Boolean getSyncRotateGroupTypeTaskTrigger(){
		return PropertiesLoaderSupportUtils.getBoolProperty("rotate-shop-integration-job.syncRotateGroupTypeTaskTrigger", true);
	}
}
