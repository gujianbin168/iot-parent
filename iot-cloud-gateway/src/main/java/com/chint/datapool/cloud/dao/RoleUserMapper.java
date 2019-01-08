package com.chint.datapool.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chint.datapool.cloud.entity.RoleUser;

@Repository
public interface RoleUserMapper extends BaseMapper<RoleUser>{
    
    int deleteByPrimaryKey(int id);

    int insertRoleUser(RoleUser record);

    int saveRoleUser(RoleUser record);

    RoleUser selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(RoleUser record);

    int updateByPrimaryKey(RoleUser record);
    
    List<RoleUser> findAllRoleUser();
}