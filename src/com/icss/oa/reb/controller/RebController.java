package com.icss.oa.reb.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.plan.pojo.Plan;
import com.icss.oa.reb.pojo.Reb;
import com.icss.oa.reb.service.RebService;
import com.icss.oa.system.pojo.Employee;
@Controller
public class RebController {
	
	@Autowired
	private RebService service;
	
	@RequestMapping("/reb/add")
	public void addPlan(HttpServletRequest request,HttpServletResponse response,Reb reb){
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("empId");
		reb.setRebTime(new Date());
		Employee emp= new Employee();
		emp.setEmpId(empId);
		reb.setEmp(emp);
		service.addReb(reb);
	}
	
	@RequestMapping("/reb/delete")
	public void deletePlan(HttpServletRequest request,HttpServletResponse response,Integer rebId){
		service.deleteReb(rebId);
	}
	
	@RequestMapping("/reb/update")
	public void approve(HttpServletRequest request,HttpServletResponse response,Reb reb) {
		service.updateReb(reb);
	}
	
	@RequestMapping("/reb/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,Integer pageNum,Integer pageSize){
		if (pageNum==null) pageNum=1;
		if (pageSize==null) pageSize=10;
		int count = service.getRebCount();
		Pager pager=new Pager(count, pageSize, pageNum);
		List<Reb> list=service.queryReb(pager.getStart(), pager.getPageSize());
		System.out.println(list);
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list); 
		
		return map;
	}
	
	@RequestMapping("/reb/queryMy")
	@ResponseBody
	public HashMap<String, Object> queryMy(HttpServletRequest request,HttpServletResponse response,Integer pageNum,Integer pageSize){
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("empId");
		if (pageNum==null) pageNum=1;
		if (pageSize==null) pageSize=10;
		int count = service.getRebCountByEmpId(empId);
		Pager pager=new Pager(count, pageSize, pageNum);
		List<Reb> list=service.queryRebByEmpId(pager.getStart(), pager.getPageSize(),empId);
		System.out.println(list);
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list); 
		
		return map;
	}
	
	@RequestMapping("/reb/get")
	@ResponseBody
	public Reb get(HttpServletRequest request,HttpServletResponse response,Integer rebId) {
		return service.queryRebById(rebId);
	}
	
	@RequestMapping("/reb/checkEmp")
	@ResponseBody
	public boolean checkEmp(HttpServletRequest request,HttpServletResponse response,Integer rebEmpId) {
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("empId");
		if(empId==rebEmpId)
			return true;
		else return false;
		
			
	}

}
