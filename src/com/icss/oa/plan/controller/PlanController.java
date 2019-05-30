package com.icss.oa.plan.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icss.oa.common.Pager;
import com.icss.oa.plan.pojo.Plan;
import com.icss.oa.plan.service.PlanService;

@Controller
public class PlanController {

	
	@Autowired
	private PlanService service;
	
	@RequestMapping("/plan/add")
	public void addPlan(Plan plan){
		service.addPlan(plan);
	}
	
	@RequestMapping
	public HashMap<String, Object> query(Integer pageNum,Integer pageSize,String planTime,String planName,Integer deptId){
		if (pageNum==null) pageNum=1;
		if (pageSize==null) pageSize=10;
		int count = service.getCount(planTime, planName, deptId);
		Pager pager=new Pager(count, pageSize, pageNum);
		
		List<Plan> list=service.queryPlan(pager, planTime, planName, deptId);
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
		
		
	}
}
