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
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Job;
import com.icss.oa.system.service.JobService;


@Controller
public class JobController {
	
	@Autowired
	private JobService service;
	

	@RequestMapping("/job/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Job job) {
		service.addJob(job);
	}
	

	@RequestMapping("/job/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Job job) {
		service.updateJob(job);
	}
	

	@RequestMapping("/job/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer jobId) {
		service.deleteJob(jobId);
	}
	
	
	@RequestMapping("/job/get")
	@ResponseBody
	public Job get(HttpServletRequest request,HttpServletResponse response,Integer jobId) {
		return service.queryById(jobId);
	}
	

	@RequestMapping("/job/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum) {
		if (pageNum==null) pageNum=1;
		if (pageSize==null) pageSize=10;
		Pager pager = new Pager(service.getJobCount(), pageSize, pageNum);
		List<Job> list = service.queryJob(pager.getStart(), pager.getPageSize());
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;	
	}

}