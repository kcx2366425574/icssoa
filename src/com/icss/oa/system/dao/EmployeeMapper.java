package com.icss.oa.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.system.pojo.Employee;



public interface EmployeeMapper {

	void delete(Integer empId);

	void insert(Employee emp);

	int update(Employee emp);
	
	//查看头像
	String queryHead(String empLoginName);
	
	//根据登录名得到id
	Integer getId(String empLoginName);
	
	void updatePwd(@Param("empLoginName")String empLoginName,@Param("empPwd")String empPwd);
	
	//更新头像
	void updateHead(@Param("empLoginName")String empLoginName,@Param("empPhoto")String empPhoto);
	
	Employee queryById(Integer empid);

	Employee queryByLoginName(String empLoginName);
	
	List<Employee> queryByNothing();
	
	Integer[] queryByBirthday(String date);
	
	List<Employee> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("empName") String empName,@Param("empSex") String empSex,
			@Param("deptId") Integer deptId, @Param("jobId") Integer jobId); // instr

	int getCountByCondition(@Param("deptId") Integer deptId, 
			@Param("jobId") Integer jobId, @Param("empSex") String empSex,
			@Param("empName") String empName); 
	
	
	int getLastInsertId();
	
	void deleteMany(Integer[] ids);
	 
}