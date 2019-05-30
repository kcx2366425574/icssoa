package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Notice;
import com.icss.oa.system.service.NoticeService;

public class TestNoticeService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	NoticeService service = context.getBean(NoticeService.class);
	
	@Test
	public void testInsert() {
		Employee emp =new Employee();
		emp.setEmpId(3);
		Notice notice= new Notice("xxxx","1111", emp);		
		service.addNotice(notice);		
	}	
	
	@Test
	public void testUpdate() {
		Employee emp =new Employee();
		emp.setEmpId(3);
		Notice notice= new Notice("xxxx", "111", emp);		
		service.updateNotice(notice);	
	}
	
	@Test
	public void testDelete() {		
		service.deleteNotice(1);	
	}
	
	@Test
	public void testQueryById() {		
		
		Notice notice = service.queryById(4);
		System.out.println(notice);
		
	}
	
	@Test
	public void testQuery() {
		List<Notice> list = service.queryNotice(0,6);
		for (Notice notice : list) {
			System.out.println(notice);	
		}	
	}
	
}
