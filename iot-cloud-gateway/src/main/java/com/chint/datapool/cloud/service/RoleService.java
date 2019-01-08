package com.chint.datapool.cloud.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.chint.datapool.cloud.entity.Role;

public interface RoleService extends IService<Role> {
	Page<Role> findAllRole(Page<Role> page);
	Role findRole(Integer id);
	int insertRole(Role role);
	int updateRoleById(Role Role);
	int deleteRoleById(Integer id);
}
