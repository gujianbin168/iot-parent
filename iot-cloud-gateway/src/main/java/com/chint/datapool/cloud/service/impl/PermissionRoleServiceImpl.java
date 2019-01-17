package com.chint.datapool.cloud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chint.datapool.cloud.dao.PermissionRoleMapper;
import com.chint.datapool.cloud.entity.PermissionRole;
import com.chint.datapool.cloud.service.PermissionRoleService;

@Service
public class PermissionRoleServiceImpl extends ServiceImpl<PermissionRoleMapper, PermissionRole> implements PermissionRoleService{

	@Override
	public List<PermissionRole> findAllPermissionRole() {
		return baseMapper.findAllPermissionRole();
	}

	@Override
	public PermissionRole findPermissionRole(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertPermissionRole(PermissionRole permissionRole) {
		return baseMapper.savePermissionRole(permissionRole);
	}

	@Override
	public int updatePermissionRoleById(PermissionRole permissionRole) {
		return baseMapper.updateByPrimaryKeySelective(permissionRole);
	}

	@Override
	public int deletePermissionRoleById(Integer id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

}
