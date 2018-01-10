package com.oracle.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable {

	private Long did;
	private String dept_name;

	private List<DeptPosition> dpList=new ArrayList<DeptPosition>(); 
	
	public List<DeptPosition> getDpList() {
		return dpList;
	}
	public void setDpList(List<DeptPosition> dpList) {
		this.dpList = dpList;
	}
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	
	
}
