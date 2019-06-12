package com.icss.oa.schedule.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.schedule.pojo.Schedule;
import com.icss.oa.schedule.service.ScheduleService;
import com.icss.oa.system.pojo.Employee;
/**
 * 
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
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer schId) {
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
	@RequestMapping("/sch/query")
	@ResponseBody
	public List<Schedule> queryAll()
	{
		return service.querySchedule();
	}
	
	@RequestMapping("/sch/queryById")
	@ResponseBody
	public Schedule queryById(HttpServletRequest request,HttpServletResponse response,Integer schId) {
		return service.queryBySchId(schId);
	}
	@RequestMapping("/sch/queryByPage")
	@ResponseBody
	public HashMap<String, Object> queryByPage(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum)
	{
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;
		Pager pager=new Pager(service.getCount(), pageSize, pageNum);
		List<Schedule> list=service.queryByPage(pager);
		HashMap<String, Object> map = new HashMap<>();
		//storage page data and schedule data in map set
		map.put("pager",pager);
		map.put("list", list);
		return map;
	}
	@RequestMapping("sch/search") 
	@ResponseBody
	public HashMap<String, Object> search(HttpServletRequest request,HttpServletResponse responsep,Integer pageNum,
			Integer pageSize,Integer empId1,Integer empId2,String schName) {
		
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;		
		
		Pager pager = new Pager(service.getCountByCondition(empId1, empId2, schName), pageSize, pageNum);
		
		List<Schedule> list = service.queryByCondition(pager, empId1, empId2, schName);
		
		//在Map集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;		
	}
	/**
	 * 全文检索员工
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@RequestMapping("/sch/queryByIndex")
	@ResponseBody
	public List<Schedule> queryByIndex(HttpServletRequest request,HttpServletResponse response,String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {
	
			
		return service.querySchByIndex(queryStr);		
	}
}
