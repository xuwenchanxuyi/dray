package com.oracle.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable{

	private Long ccid;
	private String c_name;
	private String c_email;
	private String c_mobile;
	private String c_date;
	private String c_state;
	private String c_username;
	private long c_age;
	private String c_birthday;
	private String c_sex;
	private String isAllot;
	private long nums;
	public List<Customer> getList() {
		return list;
	}
	public void setList(List<Customer> list) {
		this.list = list;
	}
	private List<Customer> list=new ArrayList<Customer>();
	public long getNums() {
		return nums;
	}
	public void setNums(long nums) {
		this.nums = nums;
	}
	public String getIsAllot() {
		return isAllot;
	}
	public void setIsAllot(String isAllot) {
		this.isAllot = isAllot;
	}
	public Long getCcid() {
		return ccid;
	}
	public void setCcid(Long ccid) {
		this.ccid = ccid;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_email() {
		return c_email;
	}
	public void setC_email(String c_email) {
		this.c_email = c_email;
	}
	public String getC_mobile() {
		return c_mobile;
	}
	public void setC_mobile(String c_mobile) {
		this.c_mobile = c_mobile;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public String getC_state() {
		return c_state;
	}
	public void setC_state(String c_state) {
		this.c_state = c_state;
	}
	public String getC_username() {
		return c_username;
	}
	public void setC_username(String c_username) {
		this.c_username = c_username;
	}
	public long getC_age() {
		return c_age;
	}
	public void setC_age(long c_age) {
		this.c_age = c_age;
	}
	public String getC_birthday() {
		return c_birthday;
	}
	public void setC_birthday(String c_birthday) {
		this.c_birthday = c_birthday;
	}
	public String getC_sex() {
		return c_sex;
	}
	public void setC_sex(String c_sex) {
		this.c_sex = c_sex;
	}
	
	
}
