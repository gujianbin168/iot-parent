package com.chint.datapool.cloud.common.api.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface HasStatus {
    /**
     * 系统的四个状态值
     */
    String[] status() default "1,!11,*,*";
    String errorshow() default "状态不允许操作";
  
}
