package com.oracle.serviceImp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Users;
import com.oracle.mapper.UsersMapper;
import com.oracle.service.ModifyUserService;
import com.oracle.util.Password;

@Component("ModifyUserImp")
public class ModifyUserImp implements ModifyUserService{

	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Override
	public Users getUsersInfo(long uid) {
		// TODO Auto-generated method stub
		
		
		return usersMapper.getModifyUserInfo(uid);
	}

	
	@Override
	public long resetPassword(long id) {
		// TODO Auto-generated method stub
		
		/**
		 * 设置重置密码为111111
		 */
		String resetpwd=Password.MD5EncodePass("111111");
		
		long num=usersMapper.resetPassword(resetpwd, id);
		
		
		return num;
		
	}


	@Override
	public long delUsers(long uid) {
		// TODO Auto-generated method stub
		return usersMapper.delUsers(uid);
	}	
	
	public List<Users> getPositionByDept(String dept){
		
		List<Users> list=usersMapper.getPositionByDept(dept);
		
		System.out.println("users"+list);
		
		return list;
		
	
		
	}


	public long getRoleByPosition(String positon) {
		// TODO Auto-generated method stub
		long roleId=usersMapper.getRoleByPosition(positon);
		System.out.println("角色ID:"+roleId);
		return roleId;
	}
	
	/**
	 * 
	 * @param uidd 用户id
	 * @param u_name 用户名
	 * @param mobile 用户手机号
	 * @param u_wordstate 用户工作状态
	 * @param positon 用户职位
	 * @param did
	 * @param deptId 用户部门ID
	 * @return
	 */
	
	@Transactional
	public long updateUserInfo(long uidd,String u_name,String mobile,String u_wordstate,String positon,long did,long deptId){
		
		usersMapper.updateUsersInfo(uidd,u_name,mobile,u_wordstate,positon,did,deptId);
		
		
		return 0;
		
	}

	
	
}
		
	


