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
import com.chint.datapool.cloud.entity.Permission;
import com.chint.datapool.cloud.service.PermissionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permission")
@Slf4j
public class PermissionController {
	@Autowired
	private PermissionService permissService;
	
	private static Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@RequestMapping(value="/find/{id}")
	public BaseResponse selectPermission(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		Permission permission = permissService.findPermission(id);
		result.setData(permission);
		return result;
	}
	
	@RequestMapping(value="/allpage/{pageNumber}")
	public BaseResponse selectPermissionPage(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "6") Integer pageSize){
		BaseResponse result = new BaseResponse();
		Page page = new Page(pageNumber, pageSize);
		Page<Permission> permissionPage = permissService.findAllPermission(page);
		result.setData(permissionPage.getRecords());
		return result;
	}
	
	@RequestMapping(value="/save")
	public BaseResponse insertPermission(@RequestBody Permission permission){
		BaseResponse result = new BaseResponse();
		permissService.insertPermission(permission);
		return result;
	}
	
	@RequestMapping(value="/update")
	public BaseResponse updatePermission(@RequestBody Permission permission){
		BaseResponse result = new BaseResponse();
		permissService.updatePermissionById(permission);
		return result;
	}
	
	@RequestMapping(value="/delete/{id}")
	public BaseResponse deletePermission(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		permissService.deletePermissionById(id);
		return result;
	}
}
