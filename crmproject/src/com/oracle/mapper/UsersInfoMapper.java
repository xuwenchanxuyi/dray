package com.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oracle.entity.Users;
import com.oracle.entity.UsersInfo;

public interface UsersInfoMapper {

	public List<UsersInfo> getUserInfo();
}
