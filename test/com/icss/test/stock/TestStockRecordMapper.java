package com.icss.test.stock;

import java.text.ParseException;
import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.stock.dao.StockRecordMapper;
import com.icss.oa.stock.pojo.Stock;
import com.icss.oa.stock.pojo.StockRecord;
import com.icss.oa.system.pojo.Department;

public class TestStockRecordMapper {
	
	// 获得Spring容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	// 获得Mapper对象(dao对象）
	StockRecordMapper mapper = context.getBean(StockRecordMapper.class);
	
	// 日期转换
//	public Date inform(String str) throws ParseException {
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = format.parse(str);
//		return date;
//	}
	
	//增加数据
	@Test
	public void testInsert() throws ParseException {
		Department department = new Department();
		department.setDeptId(3);
		Stock stock = new Stock();
		stock.setStockId(2);
		
		StockRecord stockRecord = new StockRecord(department, stock, 30, Date.valueOf("2019-3-20"));
		mapper.insert(stockRecord);
	}
	
	//删除数据
	@Test
	public void testDelete() {
		mapper.delete(5);
	}
	
	//修改数据
	@Test
	public void testUpdate() throws ParseException {
		Department department = new Department();
		department.setDeptId(2);
		Stock stock = new Stock();
		stock.setStockId(4);
		
		StockRecord stockRecord = new StockRecord(4, department, stock, 30, Date.valueOf("2019-3-20"));
		mapper.update(stockRecord);
	}
	
	//根据id查询数据
	@Test
	public void testQueryById() {
		StockRecord stockRecord = mapper.queryById(3);
		System.out.println(stockRecord);
	}
	
	//条件获取记录数
	@Test
	public void testGetCount() {
		int count = mapper.getCount(2, null, null);
		System.out.println(count);
	}
	
	//条件查询
	@Test
	public void testQueryByCond() {
		List<StockRecord> list = mapper.queryByCond(0, 3, 2, null, null);
		for (StockRecord stockRecord : list) {
			System.out.println(stockRecord);
		}
	}

}
