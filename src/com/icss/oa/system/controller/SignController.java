package com.icss.oa.system.controller;

import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Sign;
import com.icss.oa.system.service.EmployeeService;
import com.icss.oa.system.service.SignService;

@Controller
public class SignController {

	@Autowired
	private SignService service;
	
	@Autowired
	private EmployeeService empService;
	
	@RequestMapping("/sign/query")
	public HashMap<String, Object> querySign(HttpServletRequest request,HttpServletResponse response,Integer signEmpId,Integer pageNum,Integer pageSize){
		if (pageNum==null) pageNum=1;
		if (pageSize==null) pageSize=10;
		int count=service.getCount(signEmpId);
		Pager pager=new Pager(count, pageSize, pageNum);
		List<Sign>list= service.query(signEmpId, pager);
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	@RequestMapping("/sign/add")
	public void addSign(HttpServletRequest request, HttpServletResponse response, String EmpLoginName,String signTime) throws ParseException {
		HttpSession session = request.getSession();
		
		String empLoginName = (String) session.getAttribute("empLoginName");
		Employee emp = empService.queryEmpByLoginName(empLoginName);
		Integer signEmpId = emp.getEmpId();
		
		service.insert(signEmpId, signTime);
	}
	
}
