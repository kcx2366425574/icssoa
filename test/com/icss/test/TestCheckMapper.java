package com.icss.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.CheckMapper;
import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Check;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;

public class TestCheckMapper {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	CheckMapper mapper = context.getBean(CheckMapper.class);
	

	@Test
	public void testInsert() {	
		Employee employee =new Employee();
		employee.setEmpId(1);
		Check check = new Check(employee, new Date());		
		mapper.insert(check);
	}
	

	@Test
	public void testQueryByEmpId() {	
		List<Check> list = mapper.queryByEmpId(1,0,6);
		for (Check check : list) {
			System.out.println(check);
		}
	}
	
	@Test
	public void testQuery() {	
		List<Check> list = mapper.query(0,4);
		for (Check check : list) {
			System.out.println(check);
		}
	}
}
