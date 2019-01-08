package com.chint.datapool.cloud.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.chint.datapool.cloud.entity.PermissionRole;

public interface PermissionRoleService extends IService<PermissionRole>{
	List<PermissionRole> findAllPermissionRole();
	PermissionRole findPermissionRole(Integer id);
	int insertPermissionRole(PermissionRole permissionRole);
	int updatePermissionRoleById(PermissionRole permissionRole);
	int deletePermissionRoleById(Integer id);
}
