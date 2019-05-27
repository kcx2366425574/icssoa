package com.icss.oa.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.system.pojo.Check;
import com.icss.oa.system.pojo.Department;

public interface CheckMapper {

	void insert(Check check);
	List<Check> query(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	List<Check> queryById(int id);
	
}
