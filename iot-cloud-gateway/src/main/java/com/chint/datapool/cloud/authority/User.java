package com.chint.datapool.cloud.authority;

import java.util.List;
import com.chint.datapool.cloud.common.api.annotation.SensitiveInfo;
import com.chint.datapool.cloud.common.api.annotation.SensitiveType;
import io.swagger.annotations.ApiModel;
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel
public class User {
	/**
	 * 用户ID
	 **/
	private String userId;

	/**
	 * 用户ID
	 **/
	@SensitiveInfo(SensitiveType.NONE) 
	private String token;
	/**
	 * 用户名
	 **/
	private String userName;
 

	/**
	 * 角色
	 **/
	private List<MRoleEntity> role;
 
	
 

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<MRoleEntity> getRole() {
		return role;
	}

	public void setRole(List<MRoleEntity> role) {
		this.role = role;
	}

 

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
