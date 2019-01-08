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
import com.chint.datapool.cloud.entity.PermissionRole;
import com.chint.datapool.cloud.service.PermissionRoleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissionrole")
@Slf4j
public class PermissionRoleController {
	
	@Autowired
	private PermissionRoleService permissionRoleService;
	
	private static Logger logger = LoggerFactory.getLogger(PermissionRoleController.class);
	
	@RequestMapping(value="/find/{id}")
	public BaseResponse selectPermissionRole(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		PermissionRole permissionRole = permissionRoleService.findPermissionRole(id);
		result.setData(permissionRole);
		return result;
	}
	
	@RequestMapping(value="/save")
	public BaseResponse insertPermissRole(@RequestBody PermissionRole permissionRole){
		BaseResponse result = new BaseResponse();
		permissionRoleService.insertPermissionRole(permissionRole);
		return result;
	}
	
	@RequestMapping(value="/update")
	public BaseResponse updatePermissRole(@RequestBody PermissionRole permissionRole){
		BaseResponse result = new BaseResponse();
		permissionRoleService.updatePermissionRoleById(permissionRole);
		return result;
	}
	
	@RequestMapping(value="/delete/{id}")
	public BaseResponse deletePermissRole(@PathVariable Integer id){
		BaseResponse result = new BaseResponse();
		permissionRoleService.deletePermissionRoleById(id);
		return result;
	}
}
