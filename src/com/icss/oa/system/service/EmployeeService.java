package com.icss.oa.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Employee;

@Service
@Transactional(rollbackFor=Exception.class)
public class EmployeeService {

	@Autowired
	private EmployeeMapper mapper;
	
	public void addEmployee(Employee emp){
		mapper.insert(emp);
	}
	
	public void deleteEmployee(Integer empId){
		mapper.delete(empId);
	}
	
	public void updateEmployee(Employee emp){
		mapper.update(emp);
	}
	@Transactional(readOnly=true)
	public List<Employee> queryEmployee(Pager pager,String empName, 
			String empSex,Integer deptId,  Integer jobId){
		return mapper.queryByCondition(pager.getStart(),pager.getPageSize(), empName, empSex, deptId, jobId);
	}
	
	@Transactional(readOnly=true)
	public Employee queryEmpByLoginName(String empLoginName){
		return mapper.queryByLoginName(empLoginName);
	}
	
	@Transactional(readOnly=true)
	public int getEmpCount(String empName, 
			String empSex,Integer deptId,  Integer jobId){
		return mapper.getCountByCondition(deptId, jobId, empSex, empName);
	}
	
	@Transactional(readOnly=true)
	public Employee getById(Integer empId){
		return mapper.queryById(empId);
	}
	
}
