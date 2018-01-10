package com.oracle.service;

import java.util.Map;

public interface UsersInfoService {
	
	/**
	 * 得到所有的用户信息
	 * @return UsersInfo
	 */
	public Map<String,Object> getAllUsersInfo(String userName,int currentPage,int PageSize);

}
