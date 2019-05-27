package com.icss.oa.plan.service;

/**
 * 部门计划
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.plan.dao.PlanMapper;
import com.icss.oa.plan.pojo.Plan;

@Service
@Transactional(rollbackFor=Exception.class)
public class PlanService {

	@Autowired
	private PlanMapper mapper;
	
	//增加部门计划
	public void addPlan(Plan plan){
		mapper.insert(plan);
	}
	
	//删除部门计划
	public void deletePlan(String planName){
		mapper.delete(planName);
	}
	
	//修改部门计划
	public void updatePlan(Plan plan){
		mapper.update(plan);
	}
	
	//查询部门计划
	public List<Plan> queryPlan(String planTime,String planName,String deptName){
		return mapper.queryByCondition(planTime, planName, deptName);
	}
}
