package com.oracle.service;

import java.util.Map;

public interface TelemarketerManagerService {

	public Map<String,Object> getTelemarketerManagerUsers(int currentPage,int pageSize);
}
