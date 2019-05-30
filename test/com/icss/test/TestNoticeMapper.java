package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.icss.oa.system.dao.NoticeMapper;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.pojo.Notice;

public class TestNoticeMapper {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	NoticeMapper mapper = context.getBean(NoticeMapper.class);
	
	@Test
	public void testInsert() {
		Employee emp =new Employee();
		emp.setEmpId(3);
		Notice notice= new Notice("xxxx","1111", emp);		
		mapper.insert(notice);		
	}	
	
	@Test
	public void testUpdate() {
		Employee emp =new Employee();
		emp.setEmpId(3);
		Notice notice= new Notice(3,"xxxx", "111", emp);		
		mapper.update(notice);	
	}
	
	@Test
	public void testDelete() {		
		mapper.delete(1);	
	}
	
	@Test
	public void testQueryById() {		
		
		Notice notice = mapper.queryById(4);
		System.out.println(notice);
		
	}
	
	@Test
	public void testQuery() {
		List<Notice> list = mapper.query(0,6);
		for (Notice notice : list) {
			System.out.println(notice);	
		}	
	}
}
