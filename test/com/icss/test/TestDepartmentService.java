package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;

import javafx.scene.DepthTest;


public class TestDepartmentService {
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	DepartmentService service = context.getBean(DepartmentService.class);
	
	@Test
	public void testAddDept() {
		Department dept = new Department("aaa","aaa");
		service.addDept(dept);
	}
	
	@Test
	public void testdeleteDept() {
		
		service.deleteDept(7);
	}
	
	@Test
	public void testupdateDept() {
		Department dept = new Department("aaa","aaa");
		service.addDept(dept);
	}
	
	@Test
	public void testQueryDept(){
		List<Department> list= service.queryDept(0, 6);
		for (Department dept : list) {
			System.out.println(dept);
		}
	}
	
	
}