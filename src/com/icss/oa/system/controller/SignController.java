package com.icss.oa.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	@ResponseBody
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
	
	//查询本月签到数
	@RequestMapping("/sign/queryByMonth")
	@ResponseBody
	public List<String> queryByMonth(HttpServletRequest request,HttpServletResponse response){
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		Employee emp = empService.queryEmpByLoginName(empLoginName);
		Integer signEmpId = emp.getEmpId();
		List<Sign>list= service.queryByMonth(signEmpId);
		List<String> day = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		
		for (Sign sign : list) {
		    day.add(formatter.format(sign.getSignTime()));
		}
		
		return day;
	}
	
	@RequestMapping("/sign/add")
	public void addSign(HttpServletRequest request, HttpServletResponse response, String EmpLoginName,String signTime) throws ParseException {
		HttpSession session = request.getSession();
		
		String empLoginName = (String) session.getAttribute("empLoginName");
		Employee emp = empService.queryEmpByLoginName(empLoginName);
		Integer signEmpId = emp.getEmpId();
		System.out.println(signEmpId +" "+signTime);
		service.insert(signEmpId, signTime);
	}
	
}
