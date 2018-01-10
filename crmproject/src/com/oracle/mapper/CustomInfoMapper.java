package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.CustomInfo;

public interface CustomInfoMapper {

	/**
	 * ��CustomInfo���в�����������Ա������
	 * @param cus
	 * @return
	 */
	public long insertInfo(CustomInfo cus);
	/**
	 * ���ݵ���Ա����id��ѯ����������������Ա��������
	 * @param id
	 * @return
	 */
	public List<CustomInfo> getTelemarketerCustomer(@Param("cid")long id);
	/**
	 * ����Ա�������û�״̬
	 * @param id
	 * @param state
	 * @param planDate
	 * @return
	 */
	public long updateCustomInfo(@Param("id")long id,@Param("state")String state);
	/**
	 * �õ�����·���ͻ�������
	 * @return
	 */
	public List<CustomInfo> getMonthAllotNum();
	/**
	 * �õ�ĳ��״̬������
	 * @param state
	 * @return
	 */
	public List<CustomInfo> getStateNum(@Param("state")String state);
	/**
	 * �õ�һ���·����������
	 * @return
	 */
	public List<CustomInfo> getAllNumber(@Param("id")long id);
	/**
	 * ͨ��group by ���õ�����״̬������
	 * @param id
	 * @return
	 */
	public List<CustomInfo> getStateAllNum(@Param("id") long id);
	
	/**
	 * ����Ա����ȡ�ͻ�����
	 * @param id
	 * @return
	 */
	public List<CustomInfo> getNetwordConsultCustomer(@Param("name")String name);
}
