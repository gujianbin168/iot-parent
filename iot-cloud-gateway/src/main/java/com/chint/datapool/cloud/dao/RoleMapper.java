package com.chint.datapool.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.chint.datapool.cloud.entity.Role;

@Repository
public interface RoleMapper extends BaseMapper<Role>{
    
    int deleteByPrimaryKey(int id);

    int insertRole(Role record);

    int saveRole(Role record);

    Role selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> findAllRole(Pagination page);
}