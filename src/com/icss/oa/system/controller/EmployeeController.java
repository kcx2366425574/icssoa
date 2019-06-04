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
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	//增加员工
	@RequestMapping("/employee/add")
	public void addEmployee(HttpServletRequest request,HttpServletResponse response,Employee emp){
		service.addEmployee(emp);
	}
	
	//根据id删除员工
	@RequestMapping("/employee/delete")
	public void delete(Integer empId){
		service.deleteEmployee(empId);
	}
	
	@RequestMapping("/employee/getAll")
	@ResponseBody
	public List<Employee> getAll(){
		return service.queryByNothing();
	}

	
	//根据查询条件动态查询员工
	@RequestMapping("/employee/query")
	@ResponseBody
	public HashMap<String, Object> query(Integer pageNum,Integer pageSize,String empName,String empSex,Integer deptId,Integer jobId){
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
	public void updateEmp(Employee emp){
		service.updateEmployee(emp);
	}
	
	//根据登录名查询员工是否存在
	@RequestMapping("/employee/checkLoginName")
	@ResponseBody
	public boolean queryEmp(String empLoginName){
		Employee emp = service.queryEmpByLoginName(empLoginName);
		if (emp ==null ) 
			return true;
		return false;
	}
	
	
	//根据id查询员工
	@RequestMapping("/employee/get")
	@ResponseBody
	public Employee getEmpById(Integer empId){
		return service.getById(empId);
	}
	
}
