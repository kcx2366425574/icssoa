package com.icss.oa.analyze.dao;

import java.util.List;
import java.util.Map;

import com.icss.oa.analyze.dto.DeptEmpCount;

public interface AnalyzeMapper {
	
	//统计每个部门的员工人数
	List<DeptEmpCount> queryDeptEmpCount();
	
	//统计每个部门最高和最低工资
	List<Map<String, Object>> querySalMinMax();
	
	//统计每种职务的员工人数
	List<Map<String, Object>> queryJobEmpCount();

}