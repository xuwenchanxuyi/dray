package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.ConsultRecord;
import com.oracle.entity.Customer;

public interface ConsultRecordMapper {

	public long insertConsult(ConsultRecord cr);

	/**
	 * ��ѯʦ��¼�󣬵õ���������������ѯʦ�Ŀͻ���Ϣ
	 * @param id
	 * @return
	 */
	public List<ConsultRecord> getConselorById(@Param("id")long id);

	public long updateConsultRecord(@Param("id")long id,@Param("c_state")String c_state,@Param("reason") String reason, @Param("linktime")String linktime,@Param("userid")long userid);

	/**
	 * 
	 * @param time
	 * @return
	 */
	public long timeTest(Customer cr);

}
