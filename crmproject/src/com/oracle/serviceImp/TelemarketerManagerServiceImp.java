package com.oracle.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.entity.Users;
import com.oracle.mapper.UsersMapper;
import com.oracle.service.TelemarketerManagerService;

@Component("TelemarketerManagerServiceImp")
public class TelemarketerManagerServiceImp implements TelemarketerManagerService {

	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	@Override
	public Map<String, Object> getTelemarketerManagerUsers(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		List<Users> list=usersMapper.TelemarketerManagerEmplyee();
		PageHelper.startPage(currentPage,pageSize);
		PageInfo<Users> page=new PageInfo<Users>(list);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		return map;
	}

}
