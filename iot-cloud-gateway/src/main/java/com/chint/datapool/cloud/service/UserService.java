package com.chint.datapool.cloud.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.chint.datapool.cloud.entity.User;


public interface UserService extends IService<User>{
	Page<User> findAllUser(Page<User> page);
	User findUser(Integer id);
	int insertUser(User user);
	int updateUserById(User user);
	int deleteUserById(Integer id);
}
