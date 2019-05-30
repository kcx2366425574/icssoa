package com.icss.oa.system.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Check;
import com.icss.oa.system.service.CheckService;

@Controller
public class CheckController {
	
	@Autowired
	private CheckService service;
	
	@RequestMapping("/check/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Check check) {
		service.addCheck(check);
	}

	@RequestMapping("/check/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,int pageSize,int pageNum) {
		Pager pager = new Pager(service.getCheckCount(), pageSize, pageNum);
		List<Check> list = service.queryCheck(pager.getStart(), pager.getPageSize());
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;	
	}
	
	@RequestMapping("/check/queryByEmpId")
	@ResponseBody
	public HashMap<String, Object> queryByEmpId(HttpServletRequest request,HttpServletResponse response,int empId,int pageSize,int pageNum) {
		Pager pager = new Pager(service.getCheckEmpCount(), pageSize, pageNum);
		List<Check> list = service.queryByEmpId(empId,pager.getStart(), pager.getPageSize());
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;	
	}
	

}
