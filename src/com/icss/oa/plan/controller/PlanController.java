package com.icss.oa.plan.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.plan.pojo.Plan;
import com.icss.oa.plan.service.PlanService;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Job;

@Controller
public class PlanController {

	
	@Autowired
	private PlanService service;
	
	@RequestMapping("/plan/add")
	public void addPlan(HttpServletRequest request,HttpServletResponse response,Plan plan){
		service.addPlan(plan);
		System.out.println(plan.getDept().getDeptId());
	}
	
	@RequestMapping("/plan/delete")
	public void deletePlan(HttpServletRequest request,HttpServletResponse response,Integer planId){
		service.deletePlan(planId);
	}
	@RequestMapping("/plan/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Plan plan) {
		service.updatePlan(plan);
	}
	
	@RequestMapping("/plan/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,Integer pageNum,Integer pageSize,String planTime,String planName,Integer deptId){
		if (pageNum==null) pageNum=1;
		if (pageSize==null) pageSize=10;
		int count = service.getCount(planTime, planName, deptId);
		Pager pager=new Pager(count, pageSize, pageNum);
		List<Plan> list=service.queryPlan(pager, planTime, planName, deptId);
		System.out.println(list);
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);
		
		return map;
	}
	@RequestMapping("/plan/get")
	@ResponseBody
	public Plan get(HttpServletRequest request,HttpServletResponse response,Integer planId) {
		return service.queryById(planId);
	}
}
