package com.chint.datapool.cloud.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.chint.datapool.cloud.redis.service.RedisService;

/**
 * 拦截器加载
 */
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
	
	@Autowired
    private RedisService redisService;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，连接以/admin为前缀的 url路径
        registry.addInterceptor(new CheckRolesInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new CheckUserInterceptor()).addPathPatterns("/admin/**");
        //Token校验
        registry.addInterceptor(new CheckTokenInterceptor(redisService)).addPathPatterns("/**");
    }
}