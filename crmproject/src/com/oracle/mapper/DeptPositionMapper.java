package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.DeptPosition;

public interface DeptPositionMapper {

	/**
	 * 通过部门查找职位
	 * @param dept
	 * @return
	 */
	List<DeptPosition> searchPosition(@Param("dept")String dept);

	DeptPosition adminSaveUser(@Param("position")String position);

	
}
