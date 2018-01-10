package com.oracle.service;

import java.util.List;
import java.util.Map;

import com.oracle.entity.CustomInfo;

public interface TelemarketerService {
	
	public Map<String,Object> getTelemarketerCustomer(int currentPage,int pageSize,long id);

}
