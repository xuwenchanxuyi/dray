package com.oracle.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.entity.ConsultRecord;
import com.oracle.entity.Customer;
import com.oracle.mapper.ConsultRecordMapper;
import com.oracle.service.CounselorService;

@Component("CounselorServiceImp")
public class CounselorServiceImp implements CounselorService {

	@Resource(name="consultRecordMapper")
	private ConsultRecordMapper consult;
	@Override
	public Map<String, Object> getConselorById(int currentPage, int pageSize, long id) {
		// TODO Auto-generated method stub
		List<ConsultRecord> list=consult.getConselorById(id);
		PageHelper.startPage(currentPage,pageSize);
		PageInfo<ConsultRecord> page=new PageInfo<ConsultRecord>(list);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		return map;
	}
	/**
	 * 咨询师询问后修改ConsultRecord
	 * @param parseLong
	 * @param c_state
	 * @param reason
	 * @param linktime
	 * @return
	 */
	public long updateConsultRecord(long parseLong, String c_state, String reason, String linktime,long userid) {
		// TODO Auto-generated method stub
		return consult.updateConsultRecord(parseLong,c_state,reason,linktime,userid);
	}
	public long timeSearch(Customer cr) {
		// TODO Auto-generated method stub
		long num=consult.timeTest(cr);
		return num;
	}
	
	

}
