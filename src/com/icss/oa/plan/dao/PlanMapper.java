package com.icss.oa.plan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.plan.pojo.Plan;

public interface PlanMapper {
 
	void insert(Plan plan);
	
	List<Plan> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,@Param("planTime")String planTime,@Param("planName") String planName,@Param ("deptId")Integer deptId);
	
	int getQueryCount(@Param("planTime")String planTime,@Param("planName") String planName,@Param ("deptId")Integer deptId);
	
	void delete(String planName);
	
	void update(Plan plan);
}