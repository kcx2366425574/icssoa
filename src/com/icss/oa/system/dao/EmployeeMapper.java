package com.icss.oa.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.system.pojo.Employee;



public interface EmployeeMapper {

	void delete(String empName);

	void insert(Employee emp);

	int update(Employee emp);

	
	List<Employee> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("empName") String empName,@Param("empSex") String empSex,
			@Param("deptId") Integer deptId, @Param("jobId") Integer jobId); // instr

	int getCountByCondition(@Param("deptId") Integer deptId, 
			@Param("jobId") Integer jobId, @Param("empSex") String empSex,
			@Param("empName") String empName); 
	 
}