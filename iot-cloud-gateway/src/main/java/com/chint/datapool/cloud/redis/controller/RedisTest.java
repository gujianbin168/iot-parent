package com.chint.datapool.cloud.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chint.datapool.cloud.common.base.BaseResponse;
import com.chint.datapool.cloud.controller.StudentController;
import com.chint.datapool.cloud.redis.service.RedisService;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisTest {
	private static Logger logger = LoggerFactory.getLogger(RedisTest.class);
	
    @Autowired
    RedisService redisService;
    
	@RequestMapping("/set/{key}")
	public BaseResponse set(@PathVariable String key) {
		BaseResponse result = new BaseResponse();
		redisService.set("firstKey", key);
		System.out.println("-------"+key);
		result.setMsg("添加成功");
		logger.info("添加成功");
		return result;
	}
	
	@RequestMapping("/get/{key}")
	public BaseResponse get(@PathVariable String key) {
		BaseResponse result = new BaseResponse();
		String str = redisService.get(key);
		System.out.println("-------"+str);
		result.setMsg("获取成功");
		logger.info("获取成功");
		return result;
	}
    
	@RequestMapping("/del/{key}")
	public BaseResponse del(@PathVariable String key) {
		BaseResponse result = new BaseResponse();
		redisService.del(key);
		String str = redisService.get(key);
		System.out.println("-------"+str);
		result.setMsg("删除成功");
		logger.info("删除成功");
		return result;
	}

 }
