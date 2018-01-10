package com.oracle.serviceImp;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oracle.entity.Users;
import com.oracle.mapper.UsersMapper;
import com.oracle.service.UserRoleInfo;

@Component("UsersRoleInfoImp")
public class UsersRoleInfoImp implements UserRoleInfo {

	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Override
	public Users getUserRoleInfo(long userid) {
		// TODO Auto-generated method stub
		return usersMapper.getUserRoleInfo(userid);
	}

	

}
