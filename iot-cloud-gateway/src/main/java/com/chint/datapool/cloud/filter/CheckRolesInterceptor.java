package com.chint.datapool.cloud.filter;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chint.datapool.cloud.common.api.annotation.RequestRoles;
import com.chint.datapool.cloud.common.exception.ChintApiException;
import com.chint.datapool.cloud.common.util.HttpsUtils;

/**
 * 校验角色
 */
public class CheckRolesInterceptor implements HandlerInterceptor {

    // 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getServletPath();
        if (url != null
                && (url.indexOf("/images/") >= 0
                || url.indexOf("/image/") >= 0
                || url.indexOf("/authentication") >= 0
                || url.indexOf("/swagger-resources") >= 0
                || url.indexOf("/swagger") == 0)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 获取出方法上的RequestRoles注解
        RequestRoles roles = method.getAnnotation(RequestRoles.class);
        if (roles == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        if (roles.value().length > 0) {
            // 如果权限配置不为空, 则取出配置值
            String[] reqRoles = roles.value();
            Set<String> authSet = new HashSet<>();
            for (String authority : reqRoles) {
                // 将权限加入一个set集合中
                authSet.add(authority);
            }
            //获取参数用户信息数据
            String param = HttpsUtils.getBodyString(request);
            JSONObject obj = JSON.parseObject(param);
            String strObj = obj.getString("chintUser");
            JSONObject userObj = JSON.parseObject(strObj);
            String roleArrayStr = userObj.getString("role");//？？？？
            if (StringUtils.isNotBlank(roleArrayStr)) {
                if (authSet.contains(roleArrayStr)) {
                    // 校验通过返回true, 否则拦截请求
                    return true;
                }
            }
        }
        // 拦截之后返回错误信息
        throw new ChintApiException(ChintApiException.API_AUTH_ERR, "权限不足，请重新登录");
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
