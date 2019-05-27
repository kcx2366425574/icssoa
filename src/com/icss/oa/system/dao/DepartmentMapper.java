package com.icss.oa.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.system.pojo.Department;


public interface DepartmentMapper {
	
	void insert(Department dept);
	
	void update(Department dept);
	
	void delete(Integer deptId);
	
	Department queryById(Integer deptId);
	
	List<Department> query(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

}