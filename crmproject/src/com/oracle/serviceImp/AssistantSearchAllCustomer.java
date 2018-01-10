package com.oracle.serviceImp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.oracle.entity.ConsultRecord;
import com.oracle.entity.CustomInfo;
import com.oracle.entity.Customer;
import com.oracle.entity.Users;
import com.oracle.mapper.ConsultRecordMapper;
import com.oracle.mapper.CustomInfoMapper;
import com.oracle.mapper.CustomerMapper;
import com.oracle.mapper.UsersMapper;
import com.oracle.service.SearchAllCustomer;


@Component("AssistantSearchAllCustomer")
public class AssistantSearchAllCustomer implements SearchAllCustomer {

	@Resource(name="customerMapper")
	private CustomerMapper customerMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Resource(name="consultRecordMapper")
	private ConsultRecordMapper consult;
	
	@Resource(name="customInfoMapper")
	private CustomInfoMapper cim;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String,Object> redisTemplate;
	
	/*@Resource(name="SerializeUtil")
	private SerializeUtil serializeUitl;*/
	/**
	 * mybatis的分页查询
	 */
	@Override
	public Object getAllCustomer(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		Customer cus=new Customer();
		List<Customer> list=customerMapper.getAllCustomer();
		cus.setList(list);
		redisTemplate.opsForValue().set("customer", cus);
		Customer customer=(Customer) redisTemplate.opsForValue().get("customer");
		List<Customer> listCus=customer.getList();
		for(Customer cus2:listCus){
			System.out.println(cus2.getC_name());
		}
		//添加到redis里面
		/*redisTemplate.opsForList().rightPush("list01", list);
		//根据key从redis服务器中取出Customer表的数据
		List<Customer> CusList=(List<Customer>) redisTemplate.opsForList().range("list01", 0,1);
	    for(Customer c:CusList){
	    	System.out.println(c.getC_name());
	    }*/
		
		//使用mybatis的分页组件设置分页
		PageHelper.startPage(currentPage, pageSize);
		//将查询到的数据放到PageInfo对象中
		PageInfo<Customer> page=new PageInfo<Customer>(list);
		//定义一个Map集合
		Map<String,Object> map=new HashMap<String,Object>();
		//将查询到的数据放到map集合中
		map.put("rows", list);
		//将总数放到map集合
		map.put("total", page.getTotal());
		
		return map;
	}
	/**
	 * 销售助理登录成功之后默认显示所有未分配的所有客户，将其查询查询出来显示在界面
	 * @return
	 */
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		List<Users> list=usersMapper.getUsers();
		return list;
	}
	/**
	 * 根据用户的名字来查询出用户的id号，以便下面的为该用户分配客户
	 * @param sel
	 * @return
	 */
	public long getUsersIdByName(String sel) {
		// TODO Auto-generated method stub
		Users user=usersMapper.getUsersIdByName(sel);
		
		return user.getUidd();
	}
	/**
	 *通过Controller层传过来的CustomInfo对象将数据循环插入到CustomInfo表中
	 *
	 * @param cus
	 * @return
	 */
	public long insertCustomerInfo(CustomInfo cus) {
		// TODO Auto-generated method stub
		return cim.insertInfo(cus);
	}
	
	/**
	 * 根据得到的id将客户状态更改为已分配
	 * @param l
	 * @return
	 */
	public long updateCustomerAllotById(long id) {
		// TODO Auto-generated method stub
		return customerMapper.updateCustomerAllotById(id);
	}
	/**
	 * 销售助理点击编辑查询用户基本信息的Service层的实现方法
	 * @param id
	 * @return
	 */
	public Customer searchCustomerById(long id) {
		// TODO Auto-generated method stub
		return customerMapper.searchCustomerById(id);
	}
	/**
	 * 销售助理更新客户信息
	 * @param cus
	 * @return
	 */
	public long assistantUpdateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerMapper.assistantUpdateCustomer(customer);
	}
	/**
	 * 得到咨询师
	 * @return
	 */
	public List<Users> getCounselor() {
		// TODO Auto-generated method stub
		return usersMapper.getCounselor();
	}
	/**
	 * 插入数据到ConsultRecord
	 * @param cr
	 */
	public long insertConsultRecord(ConsultRecord cr) {
		// TODO Auto-generated method stub
		consult.insertConsult(cr);
		return 0;
	}
	
	public long assistantAddCustomerByFile(Customer cus){
		return customerMapper.addCustomer(cus);
		
	}
	/**
	 * 销售助理产生报表
	 * @return
	 */
	public List<Customer> assistantReportFile() {
		// TODO Auto-generated method stub
		return customerMapper.assistantReportFileCustomer();
	}
	
	
}
