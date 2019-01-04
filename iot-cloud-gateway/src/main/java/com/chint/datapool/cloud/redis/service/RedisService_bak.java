package com.chint.datapool.cloud.redis.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface RedisService_bak<HK, V> {

    public void hashPut(String key, HK hashKey, V value) ;

    public Map<HK, V> hashFindAll(String key) ;

    public V hashGet(String key, HK hashKey);

    public void hashRemove(String key, HK hashKey);

    public Long listPush(String key, V value) ;

    public Long listUnshift(String key, V value) ;

    public List<V> listFindAll(String key) ;

    public V listLPop(String key) ;

    public void setValue(String key, V value);

    public void setValue(String key, V value, long timeout);


    public V getValue(String key) ;

    public void remove(String key);

    public boolean expire(String key, long timeout, TimeUnit timeUnit);	
}