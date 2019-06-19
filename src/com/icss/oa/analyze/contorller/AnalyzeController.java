package com.icss.oa.analyze.contorller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.analyze.dto.DeptEmpCount;
import com.icss.oa.analyze.service.AnalyzeService;


@Controller
public class AnalyzeController {
	
	@Autowired
	private AnalyzeService service;
	
	@RequestMapping("/analyze/queryDeptEmpCount")
	@ResponseBody
	public List<DeptEmpCount> queryDeptEmpCount(HttpServletRequest request,HttpServletResponse response) {
		
		return service.queryDeptEmpCount();
	}
	
	@RequestMapping("/analyze/querySalMinMax")
	@ResponseBody
	public List<Map<String,Object>> querySalMinMax(HttpServletRequest request,HttpServletResponse response) {
		
		return service.querySalMinMax();
	}
	
	@RequestMapping("/analyze/queryJobEmpCount")
	@ResponseBody
	public List<Map<String,Object>> queryJobEmpCount(HttpServletRequest request,HttpServletResponse response) {
		
		return service.queryJobEmpCount();
	}
	
}
