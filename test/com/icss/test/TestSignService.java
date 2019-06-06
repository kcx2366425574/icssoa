package com.icss.test;

import java.text.ParseException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.service.SignService;

public class TestSignService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	SignService service = context.getBean(SignService.class);
	
	@Test
	public void testInsert() throws ParseException{
		service.insert(1, "2019-06-06 16:26:54");
	}

}
