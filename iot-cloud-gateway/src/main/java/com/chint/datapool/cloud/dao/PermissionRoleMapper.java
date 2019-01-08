package com.chint.datapool.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chint.datapool.cloud.entity.PermissionRole;

@Repository
public interface PermissionRoleMapper extends BaseMapper<PermissionRole>{
    
    int deleteByPrimaryKey(int id);

    int insertPermissionRole(PermissionRole record);

    int savePermissionRole(PermissionRole record);

    PermissionRole selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(PermissionRole record);

    int updateByPrimaryKey(PermissionRole record);
    
    List<PermissionRole> findAllPermissionRole();
}