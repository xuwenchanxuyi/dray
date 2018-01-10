package com.oracle.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable{

	private Long uidd;
	private Roles role;
	private Department department;
	private String u_name;
	private String u_password;
	private String u_roleid;
	private String u_deptid;
	private String u_state;//用户状态
	private String u_wordstate;//在职状态
	private List<JobRight> userRight=new ArrayList<JobRight>();
	private String positon;
	private String mobile;
	private String sex;
	private long rid;
	private long deptId;
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
	public Long getUidd() {
		return uidd;
	}
	public void setUidd(Long uidd) {
		this.uidd = uidd;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_roleid() {
		return u_roleid;
	}
	public void setU_roleid(String u_roleid) {
		this.u_roleid = u_roleid;
	}
	public String getU_deptid() {
		return u_deptid;
	}
	public void setU_deptid(String u_deptid) {
		this.u_deptid = u_deptid;
	}
	public String getU_state() {
		return u_state;
	}
	public void setU_state(String u_state) {
		this.u_state = u_state;
	}
	public String getU_wordstate() {
		return u_wordstate;
	}
	public void setU_wordstate(String u_wordstate) {
		this.u_wordstate = u_wordstate;
	}
	public List<JobRight> getUserRight() {
		return userRight;
	}
	public void setUserRight(List<JobRight> userRight) {
		this.userRight = userRight;
	}
	public String getPositon() {
		return positon;
	}
	public void setPositon(String positon) {
		this.positon = positon;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
