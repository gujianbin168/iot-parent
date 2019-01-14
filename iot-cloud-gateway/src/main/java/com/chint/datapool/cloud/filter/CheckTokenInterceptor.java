package com.chint.datapool.cloud.filter;

import com.alibaba.fastjson.JSONObject;
import com.chint.datapool.cloud.common.api.annotation.AuthToken;
import com.chint.datapool.cloud.common.constant.Constants;
import com.chint.datapool.cloud.redis.service.RedisService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 
 * @ClassName:  CheckTokenInterceptor   
 * @Description:Token检查   
 * @author: wusheng
 * @date:   2019年1月12日 上午8:27:34   
 *
 */
public class CheckTokenInterceptor implements HandlerInterceptor {


    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;
    
    private static Logger logger = LoggerFactory.getLogger(CheckTokenInterceptor.class);
    
    private RedisService redisService;
    
    //添加构造方法,解决service注入为null的问题
    public CheckTokenInterceptor(RedisService redisService){
    	this.redisService = redisService;
    }

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	logger.info("token检验开始"+handler.toString());
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method        method        = handlerMethod.getMethod();
        logger.info(method+"");
        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
        	
            String token = request.getHeader(httpHeaderName);
            logger.info("token is {}", token);
            String username = "";
//            Jedis  jedis    = new Jedis("localhost", 6379);
            if (token != null && token.length() != 0) {
            	System.out.println(redisService);
                username = redisService.get(token);
                logger.info("username is {}", username);
            }
            if (username != null && !username.trim().equals("")) {
                //log.info("token birth time is: {}",jedis.get(username+token));
                Long tokeBirthTime = Long.valueOf(redisService.get(token + username));
                logger.info("token Birth time is: {}", tokeBirthTime);
                Long diff = System.currentTimeMillis() - tokeBirthTime;
                logger.info("token is exist : {} ms", diff);
                if (diff > Constants.TOKEN_RESET_TIME) {
                	redisService.expire(username, Constants.TOKEN_EXPIRE_TIME);
                	redisService.expire(token, Constants.TOKEN_EXPIRE_TIME);
                    logger.info("Reset expire time success!");
                    Long newBirthTime = System.currentTimeMillis();
                    redisService.set(token + username, newBirthTime.toString());
                }

                //用完关闭
//                jedis.close();
                request.setAttribute(REQUEST_CURRENT_KEY, username);
                return true;


            } else {
                JSONObject jsonObject = new JSONObject();

                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    out = response.getWriter();
                    out.println(jsonObject);

                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }

        }
        logger.info("token检验结束");
        request.setAttribute(REQUEST_CURRENT_KEY, null);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
