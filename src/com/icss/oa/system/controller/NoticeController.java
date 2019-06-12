package com.icss.oa.system.controller;

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
import com.icss.oa.system.pojo.Notice;
import com.icss.oa.system.service.NoticeService;
@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	@RequestMapping("/notice/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Notice notice){
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("empId");
		Employee emp=new Employee();
		emp.setEmpId(empId);
		notice.setEmp(emp);
		service.addNotice(notice);
	}
	
	@RequestMapping("/notice/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Notice notice) {
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("empId");
		Employee emp=new Employee();
		emp.setEmpId(empId);
		notice.setEmp(emp);
		service.updateNotice(notice);
	}
	

	@RequestMapping("/notice/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer noticeId) {
		service.deleteNotice(noticeId);
	}
	

	@RequestMapping("/notice/get")
	@ResponseBody
	public Notice get(HttpServletRequest request,HttpServletResponse response,Integer noticeId) {
		return service.queryById(noticeId);
	}
	

	@RequestMapping("/notice/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum) {
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Pager pager = new Pager(service.getNoticeCount(), pageSize, pageNum);
		List<Notice> list = service.queryNotice(pager.getStart(), pager.getPageSize());
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
				return map;	
	}
	@RequestMapping("/notice/checkEmp")
	@ResponseBody
	public boolean checkEmp(HttpServletRequest request,HttpServletResponse response,Integer noticeEmpId) {
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("empId");
		if(empId==noticeEmpId)
			return true;
		else return false;
		
			
	}
}
