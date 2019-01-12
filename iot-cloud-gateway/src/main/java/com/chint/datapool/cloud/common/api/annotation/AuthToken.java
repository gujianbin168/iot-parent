package com.chint.datapool.cloud.common.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName:  AuthToken   
 * @Description:加上该注解的类http请求时需要进行token鉴权 
 * @author: wusheng
 * @date:   2019年1月11日 上午9:08:37   
 *
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthToken {

}
