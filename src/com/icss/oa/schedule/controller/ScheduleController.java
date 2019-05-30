package com.icss.oa.schedule.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.schedule.pojo.Schedule;
import com.icss.oa.schedule.service.ScheduleService;
/**
 * ÈÎÎñ¿ØÖÆÆ÷
 * @author Administrator
 *
 */

@Controller
public class ScheduleController {
	@Autowired
	private ScheduleService service;
	/**
	 * add schedule
	 * @param request
	 * @param response
	 * @param sch
	 */
	@RequestMapping("/sch/add")
	
	public void add(HttpServletRequest request,HttpServletResponse response,Schedule  sch)
	{
		service.addSchedule(sch);
	}
	/**
	 * delete schedule
	 * @param request
	 * @param response
	 * @param schId
	 */
	@RequestMapping("/sch/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer schId) {
		service.deleteSchedule(schId);
	}
	/**
	 * update schedule
	 * @param request
	 * @param response
	 * @param sch
	 */
	@RequestMapping("/sch/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Schedule sch) {
		service.updateSchedule(sch);
	}
	@RequestMapping("/sch/queryById")
	@ResponseBody
	public Schedule queryById(HttpServletRequest request,HttpServletResponse response,Integer schId) {
		return service.queryBySchId(schId);
	}
	@RequestMapping("/sch/queryByPage")
	@ResponseBody
	public HashMap<String, Object> queryByPage(HttpServletRequest request,HttpServletResponse response,int pageSize,int pageNum)
	{
		Pager pager=new Pager(service.getCount(), pageSize, pageNum);
		List<Schedule> list=service.queryByPage(pager);
		HashMap<String, Object> map = new HashMap<>();
		//storage page data and schedule data in map set
		map.put("pager",pager);
		map.put("list", list);
		return map;
	}
}
