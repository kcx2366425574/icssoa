package com.icss.oa.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;

/**
 * ���ſ�����
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	/**
	 * ���Ӳ���
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Department dept) {
		service.addDept(dept);
	}
	
	/**
	 * �޸Ĳ���
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Department dept) {
		service.updateDept(dept);
	}
	
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer deptId) {
		service.deleteDept(deptId);
	}
	
	/**
	 * ͨ��id��ѯ����
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/dept/queryById")
	@ResponseBody
	public Department queryById(HttpServletRequest request,HttpServletResponse response,Integer deptId) {
		return service.queryDeptById(deptId);
	}
	
	/**
	 * ��ѯ����
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dept/query")
	@ResponseBody
	public List<Department> query(HttpServletRequest request,HttpServletResponse response,int start,int pageSize) {
		return service.queryDept(start,pageSize);
	}

}