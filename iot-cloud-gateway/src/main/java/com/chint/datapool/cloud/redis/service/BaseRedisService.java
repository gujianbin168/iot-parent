package com.chint.datapool.cloud.redis.service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * redis的基类
 * @author gujianbin
 *
 */
public abstract class BaseRedisService {
    @Autowired
    protected RedisService redisService;
}
