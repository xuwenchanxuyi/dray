package com.oracle.entity;

import java.io.Serializable;

public class CustomInfo implements Serializable {

	private Long cuid;
	private Customer customer;
	private Users users;
	private long customerId;
	private long userId;
	private String c_state;
	private String startDate;
	private String lastfollowDate;
	private String planDate;
	private String mark;//±¸×¢
	private long allNumber;
	
	public long getAllNumber() {
		return allNumber;
	}
	public void setAllNumber(long allNumber) {
		this.allNumber = allNumber;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public Long getCuid() {
		return cuid;
	}
	public void setCuid(Long cuid) {
		this.cuid = cuid;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getC_state() {
		return c_state;
	}
	public void setC_state(String c_state) {
		this.c_state = c_state;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getLastfollowDate() {
		return lastfollowDate;
	}
	public void setLastfollowDate(String lastfollowDate) {
		this.lastfollowDate = lastfollowDate;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
}
