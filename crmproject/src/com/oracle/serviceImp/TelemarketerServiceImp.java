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
		List<CustomInfo> list=customInfoMapper.getTelemarketerCustomer(id);//�õ�����Ա�������Ա��
		for(CustomInfo cus:list){
			Customer cu=cus.getCustomer();
			System.out.println("����:"+cu.getC_name());
		}
		PageHelper.startPage(currentPage, pageSize);//ͨ��PageHelper���з�ҳ
		Map<String,Object> map=new HashMap<String,Object>();//����һ��map���ϴ洢�ͻ���Ϣ��������
		PageInfo<CustomInfo> page=new PageInfo<CustomInfo>(list);//���ͻ���Ϣ�洢��PageInfo������
		map.put("rows", page.getList());//���ͻ���Ϣ�Ž�map
		map.put("total", page.getTotal());//���ͻ������Ž�map
		
		return map;
	}

	/**
	 * ��������༭�����޸Ŀͻ���״̬��ͨ��ajax��ֵ��̨����ѯ�ͻ���Ϣ
	 * @param id
	 * @return
	 */
	public Customer TelGetCustomerInfoById(long id) {
		// TODO Auto-generated method stub
		
		return customerMapper.searchCustomerById(id);
	}
	/**
	 * �����û���״̬
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
	 * �õ�����·���Ŀͻ�����
	 * @return
	 */
	public List<CustomInfo> getMonthNum() {
		// TODO Auto-generated method stub
		List<CustomInfo> list=customInfoMapper.getMonthAllotNum();
		return list;
	}
	/**
	 * �õ�ĳ��״̬������
	 * @param string
	 * @return
	 */
	public List<CustomInfo> getStateNum(String state) {
		// TODO Auto-generated method stub
		return customInfoMapper.getStateNum(state);
	}

	/**
	 * һ���·����������
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
