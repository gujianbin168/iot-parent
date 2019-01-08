package com.chint.datapool.cloud.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.chint.datapool.cloud.entity.Permission;

public interface PermissionService extends IService<Permission>{
	Page<Permission> findAllPermission(Page<Permission> page);
	Permission findPermission(Integer id);
	int insertPermission(Permission permission);
	int updatePermissionById(Permission permission);
	int deletePermissionById(Integer id);
}
