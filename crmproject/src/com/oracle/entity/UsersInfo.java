package com.oracle.entity;

import java.io.Serializable;

public class UsersInfo implements Serializable {
	
	private String u_name;//Ա������
	private String u_password;//����
	private String positon;//ְλ
	private String mobile;//�ֻ���
	private String sex;//�Ա�
	private String u_wordstate;//����״̬
	private String r_name;//��ɫ��
	private String dept_name;//��������
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
	public String getU_wordstate() {
		return u_wordstate;
	}
	public void setU_wordstate(String u_wordstate) {
		this.u_wordstate = u_wordstate;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
}
