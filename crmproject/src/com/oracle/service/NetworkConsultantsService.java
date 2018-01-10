package com.oracle.service;

public interface NetworkConsultantsService {

	/**
	 * 得到网络咨询师的客户
	 * @return
	 */
	public Object getNetwordConsultCustomer(int currentPage,int pageSize,String name);
}
