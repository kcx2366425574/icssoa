package com.icss.test.stock;

import java.text.ParseException;
import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.stock.pojo.Stock;
import com.icss.oa.stock.service.StockService;

public class TestStockService {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Service对象
	StockService service = context.getBean(StockService.class);
	
	//增加数据
	@Test
	public void testAddStock() throws ParseException {
		Stock stock = new Stock("dsfdsg", 4000, 2500, 500, Date.valueOf("2019-3-20"));
		service.addStock(stock);
	}
	
	//删除数据
	@Test
	public void testDeleteStock() {
		service.deleteStock(7);
	}
	
	//修改数据
	@Test
	public void testUpdateStock() throws ParseException {
		Stock stock = new Stock(6, "灯管", 4000, 2500, 500, Date.valueOf("2019-3-20"));
		service.updateStock(stock);
	}
	
	//根据id查询数据
	@Test
	public void testQueryStockById() {
		Stock stock = service.queryStockById(3);
		System.out.println(stock);
	}
	
	//条件获取记录数
	@Test
	public void testGetStockCountByCondition() {
		int count = service.getStockCountBuCondition(null, null);
		System.out.println(count);
	}
	
	//条件查询
	@Test
	public void testQueryStockByCondition() {
		Pager pager = new Pager(service.getStockCountBuCondition(null, null), 5, 0);
		List<Stock> list = service.queryStockByCondition(pager, null, null);
		for (Stock stock : list) {
			System.out.println(stock);
		}
	}

}
