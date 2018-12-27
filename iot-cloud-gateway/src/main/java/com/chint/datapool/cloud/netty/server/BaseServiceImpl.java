package com.chint.datapool.cloud.netty.server;

import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public void test() {
        System.out.println("调用service服务");
    }
}