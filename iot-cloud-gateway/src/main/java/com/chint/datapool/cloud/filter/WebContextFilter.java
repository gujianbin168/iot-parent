package com.chint.datapool.cloud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName:  WebContextFilter   
 * @Description:跨域访问(sosoApi),自定义的header要添加,@CrossOrigin
 * @author: wusheng
 * @date:   2019年1月11日 上午8:32:33   
 *
 */
@Configuration
public class WebContextFilter implements Filter {
 
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
    		FilterChain chain) throws IOException, ServletException {
    	HttpServletResponse resp = (HttpServletResponse)response;
    	//"*"存在风险，建议指定可信任的域名来接收响应信息，如"http://www.sosoapi.com"
    	resp.addHeader("Access-Control-Allow-Origin", "*");
    	//如果存在自定义的header参数，需要在此处添加，逗号分隔
    	resp.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With,"
    			+ "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires,"
    			+ "Content-Type, X-E4M-With,"
    			+ "Authorization");
    	resp.addHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");  
    	resp.addHeader("Access-Control-Allow-Credentials", "true");
    	
    	chain.doFilter(request, response);
    }
 
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
         
    }
 
}
