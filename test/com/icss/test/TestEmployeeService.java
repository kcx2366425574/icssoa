package com.icss.test;

/**
 * 对employee service层进行测试
 * 增删改查
 * @author kcx
 */
import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Job;
import com.icss.oa.system.service.EmployeeService;

public class TestEmployeeService {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	EmployeeService service = context.getBean(EmployeeService.class);
	
	@Test
	/**
	 * 测试插入员工数据
	 * @author kcx
	 */
	public void testInsert(){
		
		Department dept = new Department();
		dept.setDeptId(1);

		Job job = new Job();
		job.setJobId(1);
		Employee emp=new Employee("测试", "ceshi", "123456", "男", Date.valueOf("1878-03-12"), "12345678987", 3555, "ceshi@icss.com", "啥也不会", dept, job);
		service.addEmployee(emp);
		
	}
	
	@Test
	/**
	 * 测试修改员工数据
	 * @author kcx
	 */
	public void testUpdate(){
		Department dept = new Department();
		dept.setDeptId(1);

		Job job = new Job();
		job.setJobId(1);
		Employee emp=new Employee(16,"测试aaaa", "ceshi", "123456", "男", Date.valueOf("1878-03-12"), "12345678987", 7000, "ceshi@icss.com", "我会java", dept, job);
		service.updateEmployee(emp);
	}
	
	
	@Test
	/**
	 * 测试删除员工
	 * @author kcx
	 */
	public void testDelete(){
		service.deleteEmployee(1);
	}
	

}
