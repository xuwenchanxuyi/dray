package com.oracle.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.entity.CustomInfo;
import com.oracle.entity.Customer;
import com.oracle.mapper.CustomInfoMapper;
import com.oracle.mapper.CustomerMapper;
import com.oracle.service.TelemarketerService;

@Component("TelemarketerServiceImp")
public class TelemarketerServiceImp implements TelemarketerService {

	@Resource(name="customInfoMapper")
	private CustomInfoMapper customInfoMapper;
	
	@Resource(name="customerMapper")
	private CustomerMapper customerMapper;
	
	
	@Override
	public Map<String,Object> getTelemarketerCustomer(int currentPage, int pageSize, long id) {
		// TODO Auto-generated method stub
		List<CustomInfo> list=customInfoMapper.getTelemarketerCustomer(id);//得到电销员工分配的员工
		for(CustomInfo cus:list){
			Customer cu=cus.getCustomer();
			System.out.println("名字:"+cu.getC_name());
		}
		PageHelper.startPage(currentPage, pageSize);//通过PageHelper进行分页
		Map<String,Object> map=new HashMap<String,Object>();//定义一个map集合存储客户信息和总条数
		PageInfo<CustomInfo> page=new PageInfo<CustomInfo>(list);//将客户信息存储到PageInfo对象中
		map.put("rows", page.getList());//将客户信息放进map
		map.put("total", page.getTotal());//将客户总数放进map
		
		return map;
	}

	/**
	 * 电销点击编辑可以修改客户的状态，通过ajax传值后台，查询客户信息
	 * @param id
	 * @return
	 */
	public Customer TelGetCustomerInfoById(long id) {
		// TODO Auto-generated method stub
		
		return customerMapper.searchCustomerById(id);
	}
	/**
	 * 更新用户的状态
	 * @param id
	 * @param state
	 * @param planDate
	 * @return
	 */

	@Transactional
	public long TelemarketerUpdateCustomInfo(long id, String state) {
		// TODO Auto-generated method stub
		customerMapper.updateStateById(id, state);
		long num1=customInfoMapper.updateCustomInfo(id,state);
		return num1 ;
	}

	/**
	 * 得到这个月分配的客户总量
	 * @return
	 */
	public List<CustomInfo> getMonthNum() {
		// TODO Auto-generated method stub
		List<CustomInfo> list=customInfoMapper.getMonthAllotNum();
		return list;
	}
	/**
	 * 得到某个状态的数量
	 * @param string
	 * @return
	 */
	public List<CustomInfo> getStateNum(String state) {
		// TODO Auto-generated method stub
		return customInfoMapper.getStateNum(state);
	}

	/**
	 * 一个月分配的总数量
	 * @return
	 */
	public List<CustomInfo> allNumber(long id) {
		// TODO Auto-generated method stub
		return customInfoMapper.getAllNumber(id);
	}
	
	public List<CustomInfo> getStateAllNum(long id){
		return customInfoMapper.getStateAllNum(id);
		
	}
	
	
	
	
}
