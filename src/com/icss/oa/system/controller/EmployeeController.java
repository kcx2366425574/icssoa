package com.icss.oa.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	
	/**
	 * 登录验证
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/employee/login")	 
	@ResponseBody
	public Integer login(HttpServletRequest request,HttpServletResponse response,String empLoginName,String empPwd) {
		
		//返回登录状态
		int flag = service.checkLogin(empLoginName, empPwd);
		
		//如果登录成功，session中记录当前用户的登录名（用户id）
		if (flag == 3) {
			HttpSession session = request.getSession();
			session.setAttribute("empLoginName", empLoginName); //记录登录名
			
			Employee emp = service.queryEmpByLoginName(empLoginName);
			session.setAttribute("empId",emp.getEmpId()); //记录用户id
		}				
				
		return flag;
	}
	
	
	/**
	 * 返回登录名
	 * @param request
	 * @param response
	 * @param emp
	 */
	@RequestMapping("/employee/getLoginName")	
	@ResponseBody
	public String getLoginName(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		return empLoginName;
	}
	
	//增加员工
	@RequestMapping("/employee/add")
	public void addEmployee(HttpServletRequest request,HttpServletResponse response,Employee emp){
		service.addEmployee(emp);
	}
	
	//根据id删除员工
	@RequestMapping("/employee/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer empId){
		service.deleteEmployee(empId);
	}
	
	@RequestMapping("/employee/getAll")
	@ResponseBody
	public List<Employee> getAll(HttpServletRequest request,HttpServletResponse response){
		return service.queryByNothing();
	}

	
	//根据查询条件动态查询员工
	@RequestMapping("/employee/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,Integer pageNum,Integer pageSize,String empName,String empSex,Integer deptId,Integer jobId){
		if (pageNum==null) pageNum=1;
		if (pageSize==null) pageSize=10;
		int count=service.getEmpCount(empName, empSex, deptId, jobId);
		Pager pager=new Pager(count, pageSize, pageNum);
		List<Employee>list= service.queryEmployee(pager, empName, empSex, deptId, jobId);
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	//修改员工信息
	@RequestMapping("/employee/update")
	public void updateEmp(HttpServletRequest request,HttpServletResponse response,Employee emp){
		service.updateEmployee(emp);
	}
	
	//根据登录名查询员工是否存在
	@RequestMapping("/employee/checkLoginName")
	@ResponseBody
	public boolean queryEmp(HttpServletRequest request,HttpServletResponse response,String empLoginName){
		Employee emp = service.queryEmpByLoginName(empLoginName);
		if (emp ==null ) 
			return true;
		return false;
	}
	
	
	//根据id查询员工
	@RequestMapping("/employee/get")
	@ResponseBody
	public Employee getEmpById(HttpServletRequest request,HttpServletResponse response,Integer empId){
		return service.getById(empId);
	}
	
	//根据id查询员工
		@RequestMapping("/employee/queryByLoginName")
		@ResponseBody
		public Employee getEmpByLoginName(HttpServletRequest request,HttpServletResponse response,String empLoginName){
			return service.queryEmpByLoginName(empLoginName);
		}
	
	//头像
	@RequestMapping("/employee/updateHead")
	public void updateHead(HttpServletRequest request,HttpServletResponse response,String empPhoto){
		HttpSession session = request.getSession();
		
		String empLoginName = (String) session.getAttribute("empLoginName");
		service.updateHead(empLoginName, empPhoto);
	}
	
	/**
	 * 更新头像
	 */
	@RequestMapping("employee/queryHead")
	@ResponseBody
	public String queryHead(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
				
		return service.queryHead(empLoginName);
	}
	
	/**
	 * 全文检索员工
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@RequestMapping("employee/queryByIndex")
	@ResponseBody
	public List<Employee> queryByIndex(HttpServletRequest request,HttpServletResponse response,String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {
		
		return service.queryEmpByIndex(queryStr);		
	}
	
}
