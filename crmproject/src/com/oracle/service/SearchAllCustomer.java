package com.oracle.service;

import java.util.List;

import com.oracle.entity.Customer;

public interface SearchAllCustomer {
	
	/**
	 * ���������������ʾ���еĿͻ���Ϣ��ͨ����ѡ��ѡʵ�ַ���
	 * @return
	 */
	public Object getAllCustomer(int currentPage,int pageSize);

}
