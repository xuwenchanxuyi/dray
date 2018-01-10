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
	 * mybatis�ķ�ҳ��ѯ
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
		//��ӵ�redis����
		/*redisTemplate.opsForList().rightPush("list01", list);
		//����key��redis��������ȡ��Customer�������
		List<Customer> CusList=(List<Customer>) redisTemplate.opsForList().range("list01", 0,1);
	    for(Customer c:CusList){
	    	System.out.println(c.getC_name());
	    }*/
		
		//ʹ��mybatis�ķ�ҳ������÷�ҳ
		PageHelper.startPage(currentPage, pageSize);
		//����ѯ�������ݷŵ�PageInfo������
		PageInfo<Customer> page=new PageInfo<Customer>(list);
		//����һ��Map����
		Map<String,Object> map=new HashMap<String,Object>();
		//����ѯ�������ݷŵ�map������
		map.put("rows", list);
		//�������ŵ�map����
		map.put("total", page.getTotal());
		
		return map;
	}
	/**
	 * ���������¼�ɹ�֮��Ĭ����ʾ����δ��������пͻ��������ѯ��ѯ������ʾ�ڽ���
	 * @return
	 */
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		List<Users> list=usersMapper.getUsers();
		return list;
	}
	/**
	 * �����û�����������ѯ���û���id�ţ��Ա������Ϊ���û�����ͻ�
	 * @param sel
	 * @return
	 */
	public long getUsersIdByName(String sel) {
		// TODO Auto-generated method stub
		Users user=usersMapper.getUsersIdByName(sel);
		
		return user.getUidd();
	}
	/**
	 *ͨ��Controller�㴫������CustomInfo��������ѭ�����뵽CustomInfo����
	 *
	 * @param cus
	 * @return
	 */
	public long insertCustomerInfo(CustomInfo cus) {
		// TODO Auto-generated method stub
		return cim.insertInfo(cus);
	}
	
	/**
	 * ���ݵõ���id���ͻ�״̬����Ϊ�ѷ���
	 * @param l
	 * @return
	 */
	public long updateCustomerAllotById(long id) {
		// TODO Auto-generated method stub
		return customerMapper.updateCustomerAllotById(id);
	}
	/**
	 * �����������༭��ѯ�û�������Ϣ��Service���ʵ�ַ���
	 * @param id
	 * @return
	 */
	public Customer searchCustomerById(long id) {
		// TODO Auto-generated method stub
		return customerMapper.searchCustomerById(id);
	}
	/**
	 * ����������¿ͻ���Ϣ
	 * @param cus
	 * @return
	 */
	public long assistantUpdateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerMapper.assistantUpdateCustomer(customer);
	}
	/**
	 * �õ���ѯʦ
	 * @return
	 */
	public List<Users> getCounselor() {
		// TODO Auto-generated method stub
		return usersMapper.getCounselor();
	}
	/**
	 * �������ݵ�ConsultRecord
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
	 * ���������������
	 * @return
	 */
	public List<Customer> assistantReportFile() {
		// TODO Auto-generated method stub
		return customerMapper.assistantReportFileCustomer();
	}
	
	
}
