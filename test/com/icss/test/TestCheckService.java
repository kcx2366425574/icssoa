package com.icss.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.CheckMapper;
import com.icss.oa.system.pojo.Check;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.CheckService;

public class TestCheckService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	CheckService service = context.getBean(CheckService.class);
	

	@Test
	public void testInsert() {	
		Check check =new Check();
		service.addCheck(check);
	}
	

	@Test
	public void testQueryByEmpId() {	
		List<Check> list = service.queryByEmpId(1,0,6);
		for (Check check : list) {
			System.out.println(check);
		}
	}
	
	@Test
	public void testQuery() {	
		List<Check> list = service.queryCheck(0,4);
		for (Check check : list) {
			System.out.println(check);
		}
	}
}
