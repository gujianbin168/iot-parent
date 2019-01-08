package com.chint.datapool.cloud.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.chint.datapool.cloud.entity.RoleUser;

public interface RoleUserService extends IService<RoleUser>{
	List<RoleUser> findAllRoleUser();
	RoleUser findRoleUser(Integer id);
	int insertRoleUser(RoleUser roleUser);
	int updateRoleUserById(RoleUser roleUser);
	int deleteRoleUserById(Integer id);
}
