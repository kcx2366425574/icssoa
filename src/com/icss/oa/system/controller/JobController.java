package com.icss.oa.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.system.pojo.Job;
import com.icss.oa.system.service.JobService;

/**
 * ְ�������
 * @author Administrator
 *
 */
@Controller
public class JobController {
	
	@Autowired
	private JobService service;
	
	/**
	 * ���Ӳ���
	 * @param request
	 * @param response
	 * @param job
	 */
	@RequestMapping("/job/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Job job) {
		service.addJob(job);
	}
	
	/**
	 * �޸Ĳ���
	 * @param request
	 * @param response
	 * @param job
	 */
	@RequestMapping("/job/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Job job) {
		service.updateJob(job);
	}
	
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @param job
	 */
	@RequestMapping("/job/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer jobId) {
		service.deleteJob(jobId);
	}
	
	/**
	 * ͨ��id��ѯ����
	 * @param request
	 * @param response
	 * @param job
	 */
	@RequestMapping("/job/queryById")
	@ResponseBody
	public Job queryById(HttpServletRequest request,HttpServletResponse response,Integer jobId) {
		return service.queryById(jobId);
	}
	
	/**
	 * ��ѯ����
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/job/query")
	@ResponseBody
	public List<Job> query(HttpServletRequest request,HttpServletResponse response,int start,int pageSize) {
		return service.query(start,pageSize);
	}

}