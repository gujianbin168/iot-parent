package com.chint.datapool.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chint.datapool.cloud.common.base.BaseResponse;
import com.chint.datapool.cloud.entity.RoleUser;
import com.chint.datapool.cloud.service.RoleUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roleuser")
@Slf4j
public class RoleUserController {
	
	@Autowired
	private RoleUserService roleUserService;
	
	private static Logger logger = LoggerFactory.getLogger(RoleUserController.class);
	
	@RequestMapping(value="/find/{id}")
	public BaseResponse selectRoleUser(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		RoleUser roleUser = roleUserService.findRoleUser(id);
		result.setData(roleUser);
		return result;
	}
	
	@RequestMapping(value="/save")
	public BaseResponse insertRoleUser(@RequestBody RoleUser roleUser){
		BaseResponse result = new BaseResponse();
		roleUserService.insertRoleUser(roleUser);
		return result;
	}
	
	@RequestMapping(value="/update")
	public BaseResponse updateRoleUser(@RequestBody RoleUser roleUser){
		BaseResponse result = new BaseResponse();
		roleUserService.updateRoleUserById(roleUser);
		return result;
	}
	
	@RequestMapping(value="/delete/{id}")
	public BaseResponse deleteRoleUser(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		roleUserService.deleteRoleUserById(id);
		return result;
	}
}
