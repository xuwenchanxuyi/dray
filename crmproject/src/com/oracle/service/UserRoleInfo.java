package com.oracle.service;

import com.oracle.entity.Users;

public interface UserRoleInfo {
	
	/**
	 * ��ѯ�û���Ȩ��
	 * @param roleId
	 * @return Users
	 */
	public Users getUserRoleInfo(long userid);

}
