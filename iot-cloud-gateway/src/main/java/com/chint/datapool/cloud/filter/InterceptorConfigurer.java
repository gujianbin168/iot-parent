package com.chint.datapool.cloud.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器加载
 */
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，连接以/admin为前缀的 url路径
        registry.addInterceptor(new CheckRolesInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new CheckUserInterceptor()).addPathPatterns("/admin/**");
    }
}