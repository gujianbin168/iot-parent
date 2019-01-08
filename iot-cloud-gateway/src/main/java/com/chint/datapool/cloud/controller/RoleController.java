package com.chint.datapool.cloud.controller;

import java.util.List;

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
import com.chint.datapool.cloud.entity.Role;
import com.chint.datapool.cloud.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@RequestMapping(value="/find/{id}")
	public BaseResponse selectRole(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		Role role = roleService.findRole(id);
		result.setData(role);
		return result;
	}
	
	@RequestMapping(value="/allpage/{pageNumber}")
	public BaseResponse selectRolePage(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "6") Integer pageSize){
		BaseResponse result = new BaseResponse();
		Page page = new Page(pageNumber, pageSize);
		Page<Role> roleUsers = roleService.findAllRole(page);
		result.setData(roleUsers.getRecords());
		return result;
	}
	
	@RequestMapping(value="/save")
	public BaseResponse insertRole(@RequestBody Role role){
		BaseResponse result = new BaseResponse();
		roleService.insertRole(role);
		return result;
	}
	
	@RequestMapping(value="/update")
	public BaseResponse updateRole(@RequestBody Role role){
		BaseResponse result = new BaseResponse();
		roleService.updateRoleById(role);
		return result;
	}
	
	@RequestMapping(value="/delete/{id}")
	public BaseResponse deleteRole(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		roleService.deleteRoleById(id);
		return result;
	}
}
