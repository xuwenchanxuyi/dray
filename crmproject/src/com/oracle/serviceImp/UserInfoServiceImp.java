package com.oracle.serviceImp;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oracle.entity.Users;
import com.oracle.mapper.UsersMapper;
import com.oracle.service.UserInfoService;

@Component("UserInfoServiceImp")
public class UserInfoServiceImp implements UserInfoService {

	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Override
	public Users getUserInfo(String u_name,String u_password) {
		// TODO Auto-generated method stub
		return usersMapper.getUserInfo(u_name,u_password);
	}
	
	public void updateUserState(long id){
		usersMapper.updateState(id);
	}

	/**
	 * 点击安全退出时要更改用户状态
	 * @param id
	 */
	public void destoryUpdate(long id) {
		// TODO Auto-generated method stub
		usersMapper.destoryUpdate(id);
	}

}
