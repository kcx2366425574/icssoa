package com.icss.test.stock;

import java.text.ParseException;
import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.stock.dao.StockMapper;
import com.icss.oa.stock.pojo.Stock;

public class TestStockMapper {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	StockMapper mapper = context.getBean(StockMapper.class);
	
	// 日期转换
//	public Date inform(String str) throws ParseException {
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = format.parse(str);
//		return date;
//	}

	// 增加数据
	@Test
	public void testInsert() throws ParseException {
		Stock stock = new Stock("rrrr", 6000, 700, 60, Date.valueOf("2019-3-20"));
		mapper.insert(stock);
	}
	
	//删除数据
	@Test
	public void testDelete() {
		mapper.delete(5);
	}
	
	//修改数据
	@Test
	public void testUpdate() throws ParseException {
		Stock stock = new Stock(4, "电风扇", 700, 400, 40, Date.valueOf("2019-3-20"));
		mapper.update(stock);
	}
	
	//根据id查询数据
	@Test
	public void testQueryById() {
		Stock stock = mapper.queryById(2);
		System.out.println(stock);
	}
	
	//条件获取记录数
	@Test
	public void testGetCountByCondition() {
		int count = mapper.getCountByCondition(null, "19-02");
		System.out.println(count);
	}
	
	//条件查询
	@Test
	public void testQueryByCondition() {
		List<Stock> list = mapper.queryByCondition(0, 2, "子", null);
		for (Stock stock : list) {
			System.out.println(stock);
		}
	}
	
}
