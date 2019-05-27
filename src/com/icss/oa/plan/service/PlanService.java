package com.icss.oa.plan.service;

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
	
	public void addPlan(Plan plan){
		mapper.insert(plan);
	}
	
	public void deletePlan(String planName){
		mapper.delete(planName);
	}
	
	public void updatePlan(Plan plan){
		mapper.update(plan);
	}
	
	public List<Plan> queryPlan(String planTime,String planName,String deptName){
		return mapper.queryByCondition(planTime, planName, deptName);
	}
}
