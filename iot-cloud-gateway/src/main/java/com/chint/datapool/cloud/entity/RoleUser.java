package com.chint.datapool.cloud.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_user")
public class RoleUser {
    private int id;

    private int sysUserId;

    private int sysRoleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(int sysUserId) {
		this.sysUserId = sysUserId;
	}

	public int getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(int sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

}