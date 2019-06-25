package com.icss.oa.reb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.reb.pojo.Reb;
import com.icss.oa.system.pojo.Department;

public interface RebMapper {

void insert(Reb reb);
	
	void update(Reb reb);
	
	void delete(Integer rebId);
	
	Reb queryById(Integer rebId);
	
	int getCount();
	
	List<Reb> query(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	
	List<Reb> queryByEmpId(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("empId") Integer empId);
	
	List<Reb> queryByEmpCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("rebReason") String rebReason,@Param("rebAmount") String rebAmount,@Param("rebTime") Date rebTime,@Param("rebApprovalStatus") String rebApprovalStatus,@Param("empName") String empName);
	int getCountByEmpId(@Param("empId") Integer empId);
	
	int getCountByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("rebReason") String rebReason,@Param("rebAmount") String rebAmount,@Param("rebTime") Date rebTime,@Param("rebApprovalStatus") String rebApprovalStatus,@Param("empName") String empName);
}
