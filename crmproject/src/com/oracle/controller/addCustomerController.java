package com.oracle.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.Customer;
import com.oracle.entity.Users;
import com.oracle.serviceImp.addCustomerServiceImp;

@Controller
public class addCustomerController {
	@Resource(name="addCustomerServiceImp")
	private addCustomerServiceImp addCustomerService;
	/**
	 * 一对象的形式接收前端的传值
	 * @param customer
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	
	@RequestMapping(value="addCustomer",method=RequestMethod.POST)
	public String addCustomer(Customer customer,HttpServletRequest req,HttpServletResponse resp) throws IOException{

		HttpServletResponse response=resp;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String result="";
		HttpServletRequest request=req;
		Customer c=customer;
		ModelAndView mav=new ModelAndView();
		System.out.println(customer.getC_name());
		long num=addCustomerService.addCustomer(c, request);
		
		if(num>0){
			result="1";
		}else{
			result="0";
		}
		System.out.println(result);
		response.getWriter().write(result);
		
		return result;
		
	}

}
