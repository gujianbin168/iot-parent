package com.chint.datapool.cloud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chint.datapool.cloud.dao.PermissionMapper;
import com.chint.datapool.cloud.entity.Permission;
import com.chint.datapool.cloud.service.PermissionService;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

	@Override
	public Page<Permission> findAllPermission(Page<Permission> page) {
		page.setRecords(baseMapper.findAllPermission(page));
		return page;
	}

	@Override
	public Permission findPermission(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertPermission(Permission permission) {
		return baseMapper.savePermission(permission);
	}

	@Override
	public int updatePermissionById(Permission permission) {
		return baseMapper.updateByPrimaryKeySelective(permission);
	}

	@Override
	public int deletePermissionById(Integer id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

}
