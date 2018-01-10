package com.oracle.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.Customer;
import com.oracle.entity.Users;

public interface CustomerMapper {

	/**
	 * 添加客户方法
	 * @param customer 接收到的客户对象
	 * @return
	 */
	public long addCustomer(Customer customer);
	/**
	 * 得到所有的客户对象
	 * @return
	 */
	public List<Customer> getAllCustomer();
	/**
	 * 根据接收到的ID,来更新Customer表中的isAllot属性，将其更改为1,意义代表已分配
	 * @param id
	 * @return
	 */
	public long updateCustomerAllotById(@Param("id")long id);
	/**
	 * 销售助理点击编辑查询用户基本信息的Mapper的实现
	 * @param id
	 * @return
	 */
	public Customer searchCustomerById(@Param("id")long id);
	/**
	 * 销售助理更新客户信息
	 * @param customer
	 * @return
	 */
	public long assistantUpdateCustomer(Customer customer);
	/**
	 * 电销员工咨询之后更新用户的状态
	 * @param id
	 * @param state
	 */
	public long updateStateById(@Param("id")long id,@Param("state")String state);
	/**
	 * 销售助理产生报表
	 * @return
	 */
	public List<Customer> assistantReportFileCustomer();
	
	public Customer searchCustomerByName(@Param("name")String name);
	
	public List<Customer> getCustomerByUserName(@Param("name")String name);
	
	public  List<Customer> networdConsultantsStatistics(@Param("name")String name);
	/**
	 * 时间查询
	 * @param customer
	 * @return
	 */
	public Customer timeSearch(String date);
	
	
}
