package com.chint.datapool.cloud.common.util;

import com.chint.datapool.cloud.common.api.annotation.SensitiveInfoSerialize;



/**
 * 
 * @author gujianbin
 *
 */
public class JsonUtil {
	/**
	 * 系统本身使用JAKSON，所以脱敏使用另外的FASTJSON隔离开
	 * 这个人工确定的方法SensitiveInfoSerialize.getJson(object)，可以确保，只在日志的时候使用，
	 * 使用的时候脱敏，这样，就不会和已有的其他系统等干扰
	 * 
	 * 原来使用FASTJSON，替换原来的JAKSON，这样FASTJSON可能有种种设置不合适导致系统问题
	 */
	public static String toJSONString(Object object) {
//		ObjectMapper maskMapper = new ObjectMapper();
//		if(object!=null)
//			try {
//				return maskMapper.writeValueAsString(object);
//			} catch (JsonProcessingException e) {
//				return "";
//			}
//		else
//			return "";
		try {
			return SensitiveInfoSerialize.getJson(object);
		} catch (Exception e) {
			return " log with exceptiong"+e.getStackTrace();
		}		
	}
}
