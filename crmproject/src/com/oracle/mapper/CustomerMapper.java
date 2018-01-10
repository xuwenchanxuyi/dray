package com.oracle.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.Customer;
import com.oracle.entity.Users;

public interface CustomerMapper {

	/**
	 * ��ӿͻ�����
	 * @param customer ���յ��Ŀͻ�����
	 * @return
	 */
	public long addCustomer(Customer customer);
	/**
	 * �õ����еĿͻ�����
	 * @return
	 */
	public List<Customer> getAllCustomer();
	/**
	 * ���ݽ��յ���ID,������Customer���е�isAllot���ԣ��������Ϊ1,��������ѷ���
	 * @param id
	 * @return
	 */
	public long updateCustomerAllotById(@Param("id")long id);
	/**
	 * �����������༭��ѯ�û�������Ϣ��Mapper��ʵ��
	 * @param id
	 * @return
	 */
	public Customer searchCustomerById(@Param("id")long id);
	/**
	 * ����������¿ͻ���Ϣ
	 * @param customer
	 * @return
	 */
	public long assistantUpdateCustomer(Customer customer);
	/**
	 * ����Ա����ѯ֮������û���״̬
	 * @param id
	 * @param state
	 */
	public long updateStateById(@Param("id")long id,@Param("state")String state);
	/**
	 * ���������������
	 * @return
	 */
	public List<Customer> assistantReportFileCustomer();
	
	public Customer searchCustomerByName(@Param("name")String name);
	
	public List<Customer> getCustomerByUserName(@Param("name")String name);
	
	public  List<Customer> networdConsultantsStatistics(@Param("name")String name);
	/**
	 * ʱ���ѯ
	 * @param customer
	 * @return
	 */
	public Customer timeSearch(String date);
	
	
}
