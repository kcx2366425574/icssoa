package com.icss.oa.reb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.icss.oa.reb.pojo.Reb;
import com.icss.oa.reb.service.RebService;
@Controller
public class RebController {
	
	@Autowired
	private RebService service;
	
	@RequestMapping("/reb/add")
	public void addPlan(HttpServletRequest request,HttpServletResponse response,Reb reb){
		service.addReb(reb);
	}
	
	@RequestMapping("/reb/delete")
	public void deletePlan(HttpServletRequest request,HttpServletResponse response,Integer rebId){
		service.deleteReb(rebId);
	}
	
	@RequestMapping("/reb/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Reb reb) {
		service.updateReb(reb);
	}
	
	@RequestMapping("/reb/get")
	@ResponseBody
	public Reb get(HttpServletRequest request,HttpServletResponse response,Integer rebId) {
		return service.queryRebById(rebId);
	}
	
	

}
