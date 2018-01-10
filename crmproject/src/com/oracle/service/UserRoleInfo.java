package com.oracle.service;

import com.oracle.entity.Users;

public interface UserRoleInfo {
	
	/**
	 * 查询用户的权限
	 * @param roleId
	 * @return Users
	 */
	public Users getUserRoleInfo(long userid);

}
