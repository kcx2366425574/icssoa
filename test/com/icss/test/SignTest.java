package com.icss.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.SignMapper;

public class SignTest {

ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	SignMapper mapper = context.getBean(SignMapper.class);
	@Test
	public void testQuery(){
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		System.out.println(formatter.format(date));
	}
}
