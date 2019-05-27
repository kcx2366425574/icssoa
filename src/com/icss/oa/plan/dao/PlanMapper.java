package com.icss.oa.plan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.plan.pojo.Plan;

public interface PlanMapper {
  
	void insert(Plan plan);
	
	List<Plan> queryByCondition(@Param("planTime")String planTime,@Param("planName") String planName,@Param ("deptName")String deptName);
	
	void delete(String planName);
	
	void update(Plan plan);
}