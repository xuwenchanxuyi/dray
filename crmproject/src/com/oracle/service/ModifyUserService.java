package com.oracle.service;

import com.oracle.entity.Users;

public interface ModifyUserService {

	/**
	 * 修改用户信息的方法
	 * @param uid 接收的用户的id
	 * @return
	 */
	public Users getUsersInfo(long uid);
	
	public long resetPassword(long id);
	
	public long delUsers(long uid);
	
}
