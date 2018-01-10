package com.oracle.entity;

import java.io.Serializable;

public class JobRight implements Serializable {

	private Long jid;
	private Roles role;
	private String rightname;
	private String rightgroup;
	public String getRightgroup() {
		return rightgroup;
	}
	public void setRightgroup(String rightgroup) {
		this.rightgroup = rightgroup;
	}
	private String jr_url;
	
	public Long getJid() {
		return jid;
	}
	public void setJid(Long jid) {
		this.jid = jid;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public String getRightname() {
		return rightname;
	}
	public void setRightname(String rightname) {
		this.rightname = rightname;
	}
	public String getJr_url() {
		return jr_url;
	}
	public void setJr_url(String jr_url) {
		this.jr_url = jr_url;
	}
	
}
