package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.CustomInfo;

public interface CustomInfoMapper {

	/**
	 * 在CustomInfo表中插入分配给电销员工数据
	 * @param cus
	 * @return
	 */
	public long insertInfo(CustomInfo cus);
	/**
	 * 根据电销员工的id查询销售助理分配给电销员工的数据
	 * @param id
	 * @return
	 */
	public List<CustomInfo> getTelemarketerCustomer(@Param("cid")long id);
	/**
	 * 电销员工更新用户状态
	 * @param id
	 * @param state
	 * @param planDate
	 * @return
	 */
	public long updateCustomInfo(@Param("id")long id,@Param("state")String state);
	/**
	 * 得到这个月分配客户的总数
	 * @return
	 */
	public List<CustomInfo> getMonthAllotNum();
	/**
	 * 得到某个状态的数量
	 * @param state
	 * @return
	 */
	public List<CustomInfo> getStateNum(@Param("state")String state);
	/**
	 * 得到一个月分配的总数量
	 * @return
	 */
	public List<CustomInfo> getAllNumber(@Param("id")long id);
	/**
	 * 通过group by 语句得到所有状态的数量
	 * @param id
	 * @return
	 */
	public List<CustomInfo> getStateAllNum(@Param("id") long id);
	
	/**
	 * 网咨员工获取客户数据
	 * @param id
	 * @return
	 */
	public List<CustomInfo> getNetwordConsultCustomer(@Param("name")String name);
}
