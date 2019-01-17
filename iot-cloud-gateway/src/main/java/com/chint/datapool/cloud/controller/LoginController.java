package com.chint.datapool.cloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chint.datapool.cloud.common.api.annotation.AuthToken;
import com.chint.datapool.cloud.common.base.BaseResponse;
import com.chint.datapool.cloud.common.constant.Constants;
import com.chint.datapool.cloud.common.token.Md5TokenGenerator;
import com.chint.datapool.cloud.entity.User;
import com.chint.datapool.cloud.redis.service.RedisService;
import com.chint.datapool.cloud.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**   
 * @ClassName:  LoginController   
 * @Description:登录管理   
 * @author: wusheng
 * @date:   2019年1月11日 下午1:01:33   
 *     
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Autowired
    Md5TokenGenerator tokenGenerator;

    @Autowired
	private UserService userService;
    
    @Autowired
    private RedisService redisService;
    
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("用户登录接口")
    public BaseResponse login(@RequestBody(required = false) JSONObject userInfo) {
    	logger.info("用户登入开始userInfo:"+userInfo);
    	BaseResponse result = new BaseResponse();
        String username = userInfo.getString("username");
        String password = userInfo.getString("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> users = userService.findUserByNamePassWord(user);

        JSONObject jsObj = new JSONObject();
        if (users != null && users.size()>0 && users.get(0) != null) {
            String token = tokenGenerator.generate(username, password);
            redisService.set(username, token);
            redisService.expire(username, Constants.TOKEN_EXPIRE_TIME);
            redisService.set(token, username);
            redisService.expire(token, Constants.TOKEN_EXPIRE_TIME);
            Long currentTime = System.currentTimeMillis();
            redisService.set(token + username, currentTime.toString());

            jsObj.put("status", "登录成功");
            jsObj.put("token", token);
        } else {
        	jsObj.put("status", "登录失败,用户名或密码错误");
        	result.setCode(Constants.ResultCode.LOGING_QRCODE_ERR);
        }
        result.setData(jsObj);
        logger.info("用户登入结束");
        return result;

    }

    @ApiOperation("测试token接口")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @AuthToken
    public BaseResponse test() {
    	BaseResponse result = new BaseResponse();
    	result.setData("请求成功");
        return result;
    }

}
