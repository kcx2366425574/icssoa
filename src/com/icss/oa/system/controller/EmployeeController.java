package com.icss.oa.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@RequestMapping("/employee/add")
	public void addEmployee(HttpServletRequest request,HttpServletResponse response,Employee emp){
		service.addEmployee(emp);
	}
	
	
	@RequestMapping("/employee/delete")
	public void delete(String empName){
		service.deleteEmployee(empName);
	}
}
