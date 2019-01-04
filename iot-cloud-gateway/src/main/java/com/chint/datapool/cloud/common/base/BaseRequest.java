package com.chint.datapool.cloud.common.base;
 
import java.io.Serializable;
import com.chint.datapool.cloud.authority.User;


/**
 * 基础类查询
 * 
 * @author gujianbin
 *
 */

public class BaseRequest implements Serializable{

	private static final long serialVersionUID = -759399335985770176L;

	public User userInfo;

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}

}
