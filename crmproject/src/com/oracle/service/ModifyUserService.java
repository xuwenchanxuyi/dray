package com.oracle.service;

import com.oracle.entity.Users;

public interface ModifyUserService {

	/**
	 * �޸��û���Ϣ�ķ���
	 * @param uid ���յ��û���id
	 * @return
	 */
	public Users getUsersInfo(long uid);
	
	public long resetPassword(long id);
	
	public long delUsers(long uid);
	
}
