package com.icss.test.analyse;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.analyse.dao.StockAnalyseMapper;

public class TestStockAnalyseMapper {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	StockAnalyseMapper mapper = context.getBean(StockAnalyseMapper.class);
	
	@Test
	public void testQuery() {
		List<Map<String, Object>> list = mapper.query();
		for (Map item : list) {
			System.out.println(item);
		}
	}

}
