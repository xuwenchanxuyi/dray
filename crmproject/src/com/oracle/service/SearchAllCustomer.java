package com.oracle.service;

import java.util.List;

import com.oracle.entity.Customer;

public interface SearchAllCustomer {
	
	/**
	 * 在销售助理界面显示所有的客户信息，通过复选框勾选实现分配
	 * @return
	 */
	public Object getAllCustomer(int currentPage,int pageSize);

}
