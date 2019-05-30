package com.icss.oa.plan.service;

/**
 * 部门计划
 */
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
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
	
	//得到条件查询总记录数
	public int getCount(String planTime, String planName,Integer deptId){
		return mapper.getQueryCount(planTime, planName, deptId);
	}
	
	
	//查询部门计划
	@Transactional(readOnly=true)
	public List<Plan> queryPlan(Pager pager,String planTime,String planName,Integer deptId){
		return mapper.queryByCondition(pager.getStart(),pager.getPageSize(),planTime, planName, deptId);
	}
}
