package com.oracle.entity;

import java.io.Serializable;

public class Roles implements Serializable{

	private Long rid;
	private String r_name;
	
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	
}
