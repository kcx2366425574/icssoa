package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.JobMapper;
import com.icss.oa.system.pojo.Job;
import com.icss.oa.system.service.JobService;

public class TestJobService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	JobService service = context.getBean(JobService.class);
	
	@Test
	public void testInsert() {		
		Job job = new Job("xxxx", 111, 222);		
		service.addJob(job);		
	}	
	
	@Test
	public void testUpdate() {		
		Job job = new Job(6,"顾问", 4600, 8100);		
		service.updateJob(job);	
	}
	
	@Test
	public void testDelete() {		
		service.deleteJob(8);
	}
	
	@Test
	public void testQueryById() {		
		
		Job job = service.queryById(4);
		System.out.println(job);
		
	}
	
	@Test
	public void testQuery() {
		List<Job> list = service.queryJob(0,6);
		for (Job job : list) {
			System.out.println(job);	
		}
	}
}
