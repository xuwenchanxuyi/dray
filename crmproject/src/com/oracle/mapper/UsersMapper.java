package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.Users;

public interface UsersMapper {
	
	public Users getUserInfo(@Param("u_name")String u_name,@Param("u_password")String u_password);
	
	public Users getUserRoleInfo(@Param("userid")long userid);
	
	public List<Users> getAllUsersInfo(@Param("userName")String userName);
	
	/**
	 * 通过ID放回用户的信息
	 * @param uid
	 * @return
	 */
	public Users getModifyUserInfo(@Param("uid")long uid);
	
	public long resetPassword(@Param("resetPwd")String resetPwd,@Param("id")long id);
	
	public long delUsers(@Param("uid")long uid);
	
	/**
	 * 通过部门获取部门下的所有职位
	 * @param dept
	 * @return
	 */
	
	public List<Users> getPositionByDept(@Param("dept")String dept);

	/**
	 * 根据职位得到角色的id
	 * @param positon
	 * @return
	 */
	public long getRoleByPosition(@Param("positon")String positon);

	/**
	 * 管理员的更新用户操作
	 * @param uidd
	 * @param u_name
	 * @param mobile
	 * @param u_wordstate
	 * @param positon
	 * @param did
	 */
	public void updateUsersInfo(@Param("uidd")long uidd,@Param("u_name")String u_name,@Param("mobile")String mobile,@Param("u_wordstate")String u_wordstate,@Param("positon")String positon,@Param("did")long did,@Param("deptId")long deptId);

	/**
	 * 得到网咨员工和电销员工
	 * @return
	 */
	public List<Users> getUsers();
	
	/**
	 * 根据员工名称得到员工的ID号
	 * @param sel
	 * @return
	 */
	public Users getUsersIdByName(@Param("sel")String sel);

	/**
	 * 得到所有的咨询师
	 * @return
	 */
	public List<Users> getCounselor();

	/**
	 * 电销主管得到电销员工的信息
	 * @return
	 */
	public List<Users> TelemarketerManagerEmplyee();

	public void updateState(@Param("id")long id);

	/**
	 * 安全退出更改状态
	 * @param id
	 */
	public void destoryUpdate(@Param("id")long id);
	
	public long savaUsers(Users user);
}
