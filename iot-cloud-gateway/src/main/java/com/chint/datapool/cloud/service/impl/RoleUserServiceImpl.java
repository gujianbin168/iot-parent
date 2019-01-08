package com.chint.datapool.cloud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chint.datapool.cloud.dao.RoleUserMapper;
import com.chint.datapool.cloud.entity.RoleUser;
import com.chint.datapool.cloud.service.RoleUserService;

@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService{

	@Override
	public List<RoleUser> findAllRoleUser() {
		return baseMapper.findAllRoleUser();
	}

	@Override
	public RoleUser findRoleUser(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertRoleUser(RoleUser roleUser) {
		return baseMapper.insertRoleUser(roleUser);
	}

	@Override
	public int updateRoleUserById(RoleUser roleUser) {
		return baseMapper.updateByPrimaryKeySelective(roleUser);
	}

	@Override
	public int deleteRoleUserById(Integer id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

}
