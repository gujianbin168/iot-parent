package com.chint.datapool.cloud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chint.datapool.cloud.dao.UserMapper;
import com.chint.datapool.cloud.entity.Student;
import com.chint.datapool.cloud.entity.User;
import com.chint.datapool.cloud.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
	
	
	@Override
	public Page<User> findAllUser(Page<User> page) {
		 page.setRecords(baseMapper.findAllUser(page));
		 return page;
	}

	@Override
	public int insertUser(User user) {
		return baseMapper.insertSelective(user);
	}

	@Override
	public int updateUserById(User user) {
		return baseMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int deleteUserById(Integer id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public User findUser(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> findUserByNamePassWord(User user) {
		return baseMapper.findUserByNamePassWord(user);
	}

	@Override
	public User findUserByUserName(String username) {
		return baseMapper.selectByUserName(username);
	}

}
