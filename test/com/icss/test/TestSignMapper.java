package com.icss.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.SignMapper;
import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Sign;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;

public class TestSignMapper {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	SignMapper mapper = context.getBean(SignMapper.class);
	

	@Test
	public void testInsert() {	
		mapper.insert(1,new Date());
	}
	
	@Test
	public void testQueryByEmpId(){
		List<Sign> list = mapper.queryByEmpId(1, 0, 5);
		for (Sign sign : list) {
			System.out.println(sign);
		}
	}
	
	//查询
	@Test
	public void testQuery(){
		List<Sign> list = mapper.query( 0, 5);
		for (Sign sign : list) {
			System.out.println(sign);
		}
	}
	

}
