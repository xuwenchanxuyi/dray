package com.oracle.service;

import java.util.Map;

public interface UsersInfoService {
	
	/**
	 * �õ����е��û���Ϣ
	 * @return UsersInfo
	 */
	public Map<String,Object> getAllUsersInfo(String userName,int currentPage,int PageSize);

}
