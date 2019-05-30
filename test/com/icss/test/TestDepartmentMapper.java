package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;


public class TestDepartmentMapper {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	DepartmentMapper mapper = context.getBean(DepartmentMapper.class);
	

	@Test
	public void testInsert() {	
		Department dept = new Department("eee", "eee");		
		mapper.insert(dept);
	}
	
	@Test
	public void testUpdate() {			
		Department dept = new Department(9,"bbb","bbb");
		mapper.update(dept);
	}
	
	@Test
	public void testDelete() {	
		mapper.delete(8);
	}
	
	@Test
	public void testQueryById() {	
		Department dept = mapper.queryById(1);
		System.out.println(dept);
	}
	
	@Test
	public void testQuery() {	
		List<Department> list = mapper.query(0,4);
		for (Department dept : list) {
			System.out.println(dept);
		}
	}
	
}