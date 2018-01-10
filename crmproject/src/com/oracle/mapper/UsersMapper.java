package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.Users;

public interface UsersMapper {
	
	public Users getUserInfo(@Param("u_name")String u_name,@Param("u_password")String u_password);
	
	public Users getUserRoleInfo(@Param("userid")long userid);
	
	public List<Users> getAllUsersInfo(@Param("userName")String userName);
	
	/**
	 * ͨ��ID�Ż��û�����Ϣ
	 * @param uid
	 * @return
	 */
	public Users getModifyUserInfo(@Param("uid")long uid);
	
	public long resetPassword(@Param("resetPwd")String resetPwd,@Param("id")long id);
	
	public long delUsers(@Param("uid")long uid);
	
	/**
	 * ͨ�����Ż�ȡ�����µ�����ְλ
	 * @param dept
	 * @return
	 */
	
	public List<Users> getPositionByDept(@Param("dept")String dept);

	/**
	 * ����ְλ�õ���ɫ��id
	 * @param positon
	 * @return
	 */
	public long getRoleByPosition(@Param("positon")String positon);

	/**
	 * ����Ա�ĸ����û�����
	 * @param uidd
	 * @param u_name
	 * @param mobile
	 * @param u_wordstate
	 * @param positon
	 * @param did
	 */
	public void updateUsersInfo(@Param("uidd")long uidd,@Param("u_name")String u_name,@Param("mobile")String mobile,@Param("u_wordstate")String u_wordstate,@Param("positon")String positon,@Param("did")long did,@Param("deptId")long deptId);

	/**
	 * �õ�����Ա���͵���Ա��
	 * @return
	 */
	public List<Users> getUsers();
	
	/**
	 * ����Ա�����Ƶõ�Ա����ID��
	 * @param sel
	 * @return
	 */
	public Users getUsersIdByName(@Param("sel")String sel);

	/**
	 * �õ����е���ѯʦ
	 * @return
	 */
	public List<Users> getCounselor();

	/**
	 * �������ܵõ�����Ա������Ϣ
	 * @return
	 */
	public List<Users> TelemarketerManagerEmplyee();

	public void updateState(@Param("id")long id);

	/**
	 * ��ȫ�˳�����״̬
	 * @param id
	 */
	public void destoryUpdate(@Param("id")long id);
	
	public long savaUsers(Users user);
}
