package com.chint.datapool.cloud.netty.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取yml配置文件中的信息
 * Created by gujianbin
 */
@Component
//@ConfigurationProperties(prefix = "netty")
@PropertySource({"classpath:application.properties"})
public class NettyConfig {
	  @Value("${netty.port}")
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
