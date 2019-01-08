package com.chint.datapool.cloud.filter;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chint.datapool.cloud.common.exception.ChintApiException;
import com.chint.datapool.cloud.common.util.HttpsUtils;
import com.chint.datapool.cloud.common.util.StringUtil;
/**
 * 校验是否合法用户
 * @author shdk_gjb
 *
 */
public class CheckUserInterceptor implements HandlerInterceptor {

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String url = request.getServletPath();
        String body = HttpsUtils.getBodyString(request);
        JSONObject obj = JSON.parseObject(body);
        if(null == obj){
            throw new ChintApiException(ChintApiException.API_USER_NOT_FOUND, "系统暂时无法检测到当前用户信息,请重新登陆。");
        }
        String userStr = obj.getString("chintUser");
        if (!StringUtil.isEmpty(userStr)) {
            JSONObject userObj = JSON.parseObject(userStr);
            String userIdInRequest = userObj.getString("userId");
            String mappingUrl = "";
        }else{
            throw new ChintApiException(ChintApiException.API_USER_NOT_FOUND, "系统暂时无法检测到当前用户信息,请重新登陆。");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
