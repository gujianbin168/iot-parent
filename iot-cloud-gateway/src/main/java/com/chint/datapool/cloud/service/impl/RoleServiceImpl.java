package com.chint.datapool.cloud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chint.datapool.cloud.dao.RoleMapper;
import com.chint.datapool.cloud.entity.Role;
import com.chint.datapool.cloud.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

	@Override
	public Page<Role> findAllRole(Page<Role> page) {
		page.setRecords(baseMapper.findAllRole(page));
		return page;
	}

	@Override
	public Role findRole(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertRole(Role role) {
		return baseMapper.saveRole(role);
	}

	@Override
	public int updateRoleById(Role role) {
		return baseMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public int deleteRoleById(Integer id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

}
