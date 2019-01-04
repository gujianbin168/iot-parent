package com.chint.datapool.cloud.common.api.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface HasRoles {
    /**
     * 允许访问的用户角色
     */
    String[] value();
  
}
