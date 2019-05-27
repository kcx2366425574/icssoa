package com.icss.oa.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
