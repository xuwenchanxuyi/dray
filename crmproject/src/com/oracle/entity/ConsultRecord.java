package com.oracle.entity;

import java.io.Serializable;

public class ConsultRecord implements Serializable{

	private Long conid;
	private Users users;
	private Customer customer;
	private String cr_date;
	private String cr_reason;
	private String cr_user_state;
	private long cr_userid;
	private long cr_customerid;
	public long getCr_userid() {
		return cr_userid;
	}
	public void setCr_userid(long cr_userid) {
		this.cr_userid = cr_userid;
	}
	public long getCr_customerid() {
		return cr_customerid;
	}
	public void setCr_customerid(long cr_customerid) {
		this.cr_customerid = cr_customerid;
	}
	public Long getConid() {
		return conid;
	}
	public void setConid(Long conid) {
		this.conid = conid;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCr_date() {
		return cr_date;
	}
	public void setCr_date(String cr_date) {
		this.cr_date = cr_date;
	}
	public String getCr_reason() {
		return cr_reason;
	}
	public void setCr_reason(String cr_reason) {
		this.cr_reason = cr_reason;
	}
	public String getCr_user_state() {
		return cr_user_state;
	}
	public void setCr_user_state(String cr_user_state) {
		this.cr_user_state = cr_user_state;
	}
	
	
}
