package com.oracle.service;

import javax.servlet.http.HttpServletRequest;

import com.oracle.entity.Customer;
import com.oracle.entity.Users;

public interface addCustomerService {
	
	public long addCustomer(Customer customer,HttpServletRequest req);
}
