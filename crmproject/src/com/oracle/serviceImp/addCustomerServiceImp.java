package com.oracle.serviceImp;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.CustomInfo;
import com.oracle.entity.Customer;
import com.oracle.entity.Users;
import com.oracle.mapper.CustomInfoMapper;
import com.oracle.mapper.CustomerMapper;
import com.oracle.service.addCustomerService;

@Component("addCustomerServiceImp")
public class addCustomerServiceImp implements addCustomerService {

	@Resource(name="customerMapper")
	private CustomerMapper customerMapper;
	
	@Resource(name="customInfoMapper")
	private CustomInfoMapper customInfoMapper;
	
	@Transactional
	@Override
	public long addCustomer(Customer cus,HttpServletRequest request) {
		HttpServletRequest req=request;
		HttpSession session=req.getSession();
		Customer customer=cus;
		CustomInfo customInfo=new CustomInfo();
		Users user=(Users) session.getAttribute("user");
		System.out.println(user.getU_name());
		customer.setC_username(user.getU_name());
		// TODO Auto-generated method stub
		long num=customerMapper.addCustomer(customer);
		return num;
	}

}
