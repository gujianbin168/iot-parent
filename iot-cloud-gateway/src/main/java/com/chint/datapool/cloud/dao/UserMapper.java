package com.chint.datapool.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.chint.datapool.cloud.entity.User;

@Repository
public interface UserMapper extends BaseMapper<User>{
	List<User> findAllUser(Pagination page);
    
    int deleteByPrimaryKey(int id);

    int insertUser(User record);

    int saveUser(User record);

    User selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> findUserByNamePassWord(User user);
}