package com.oracle.service;

import java.util.Map;

public interface CounselorService {
	
	public Map<String,Object> getConselorById(int currentPage,int pageSize,long id);

}
