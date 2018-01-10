package com.oracle.serviceImp;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.entity.CustomInfo;
import com.oracle.entity.Customer;
import com.oracle.mapper.CustomInfoMapper;
import com.oracle.mapper.CustomerMapper;
import com.oracle.service.NetworkConsultantsService;

@Component("NetworkConsultantsServiceImp")
public class NetworkConsultantsServiceImp implements NetworkConsultantsService{

	@Resource(name="customInfoMapper")
	private CustomInfoMapper customMapper;
	
	@Resource(name="customerMapper")
	private CustomerMapper customerMapper;
	@Override
	public Object getNetwordConsultCustomer(int currentPage,int pageSize,String name) {
		// 本日或者本日之前录入的还没完成的客户
		List<Customer> list=customerMapper.getCustomerByUserName(name);
		PageHelper.startPage(currentPage, pageSize);
		Map<String,Object> map=new HashMap<String,Object>();
		PageInfo<Customer> page=new PageInfo<Customer>(list);
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		return map;
	}
	
	public Customer NetworkConsultantsCustomer(long id) {
		// TODO Auto-generated method stub
		
		return customerMapper.searchCustomerById(id);
	}

	public long NetworkConsultantsUpdate(long id, String state) {
		// TODO Auto-generated method stub
		return customerMapper.updateStateById(id,state);
	}

	/**
	 * 网咨员工的本月统计方法
	 * @param name
	 * @return
	 */
	public List<Customer> networdConsultantsStatistics(String name){
		return customerMapper.networdConsultantsStatistics(name);
		
	}
	

}
