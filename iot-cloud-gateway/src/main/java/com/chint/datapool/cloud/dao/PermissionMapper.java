package com.chint.datapool.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.chint.datapool.cloud.entity.Permission;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    
    int deleteByPrimaryKey(int id);

    int insertPermission(Permission record);

    int savePermission(Permission record);

    Permission selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> findAllPermission(Pagination page);
}