package com.chint.datapool.cloud.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.chint.datapool.cloud.common.base.BaseResponse;
import com.chint.datapool.cloud.entity.User;
import com.chint.datapool.cloud.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/find/{id}")
	public BaseResponse selectUser(@PathVariable Integer id){
		logger.info("根据id查询用户开始:"+id);
		BaseResponse result = new BaseResponse();
		User user = userService.findUser(id);
		result.setData(user);
		logger.info("根据id查询用户结束:"+id);
		return result;
	}
	
	@RequestMapping(value="/allpage/{pageNumber}")
	public BaseResponse selectUserPage(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "6") Integer pageSize){
		BaseResponse result = new BaseResponse();
		Page page = new Page(pageNumber, pageSize);
		Page<User> pageUsers = userService.findAllUser(page);
		result.setData(pageUsers.getRecords());
		return result;
	}
	
	@RequestMapping(value="/save")
	public BaseResponse insertUser(@RequestBody User user){
		BaseResponse result = new BaseResponse();
		int i = userService.insertUser(user);
		return result;
	}
	
	@RequestMapping(value="update")
	public BaseResponse updateUserById(@RequestBody User user){
		BaseResponse result = new BaseResponse();
		userService.updateUserById(user);
		return result;
	}
	
	@RequestMapping(value="/delete/{id}")
	public BaseResponse deleteUserById(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		userService.deleteUserById(id);
		return result;
	}
}
