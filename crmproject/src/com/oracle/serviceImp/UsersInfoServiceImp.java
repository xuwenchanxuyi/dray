package com.oracle.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.entity.Users;
import com.oracle.entity.UsersInfo;
import com.oracle.mapper.UsersInfoMapper;
import com.oracle.mapper.UsersMapper;
import com.oracle.service.UsersInfoService;


@Component("UsersInfoServiceImp")
public class UsersInfoServiceImp implements UsersInfoService {

	/**
	 * 得到所有的用户信息的实现
	 */
	
	@Resource(name="usersInfoMapper")
	private UsersInfoMapper usersInfoMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	

	@Override
	@Transactional
	public Map<String,Object> getAllUsersInfo(String userName, int currentPage, int PageSize) {
		// TODO Auto-generated method stub
		List<Users> list=usersMapper.getAllUsersInfo(userName);
		
		PageHelper.startPage(currentPage,PageSize);
		/*long num;//数据的总条数
		List<UsersInfo> list=usersInfoMapper.getUserInfo();//查找的数据
		PageInfo<UsersInfo> page=new PageInfo<UsersInfo>(list);
		
		System.out.println("----"+list);*/
		
		
		
		PageInfo<Users> page=new PageInfo<Users>(list);
		
		List<Users> list2=page.getList();
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		
		
		
		
		return map;
	}
	
	
	

}
