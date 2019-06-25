package com.icss.test.stock;

import java.text.ParseException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.stock.pojo.Stock;
import com.icss.oa.stock.pojo.StockRecord;
import com.icss.oa.stock.service.StockRecordService;
import com.icss.oa.system.pojo.Department;

public class TestStockRecordService {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Service对象
	StockRecordService service = context.getBean(StockRecordService.class);

	//增加库存记录
	@Test
	public void testAddSrd() throws ParseException {
		Department department = new Department();
		department.setDeptId(3);
		Stock stock = new Stock();
		stock.setStockId(2);
		
		StockRecord stockRecord = new StockRecord(department, stock, 30, Date.valueOf("2019-3-20"));
		service.addSrd(stockRecord);
	}
	
	//删除库存记录
	@Test
	public void testDeleteSrd() {
		service.deleteSrd(8);
	}
	
	//修改库存记录
	@Test
	public void testUpdateSrd() throws ParseException {
		Department department = new Department();
		department.setDeptId(5);
		Stock stock = new Stock();
		stock.setStockId(8);
		
		StockRecord stockRecord = new StockRecord(7, department, stock, 10, Date.valueOf("2019-3-20"));
		service.updateSrd(stockRecord);
	}
	
	//通过id查询库存记录
	@Test
	public void testQuerySrdById() {
		StockRecord stockRecord = service.querySrdById(6);
		System.out.println(stockRecord);
	}
	
	//条件获取记录数
	@Test
	public void testGetSrdCount() {
		int count = service.getSrdCount(2, null, null);
		System.out.println(count);
	}
	
	//条件查询
	@Test
	public void testQuerySrdByCond() {
		Pager pager = new Pager(service.getSrdCount(2, null, null), 5, 0);
		List<StockRecord> list = service.querySrdByCond(pager, 2, null, null);
		for (StockRecord stockRecord : list) {
			System.out.println(stockRecord);
		}
	}
	
	//导出报表
	@Test
	public void testExport() throws IOException {
		Integer pageSize = service.getSrdCount(null, null, null);
		Pager pager = new Pager(pageSize, pageSize, 0);
		FileOutputStream fos = new FileOutputStream("e:\\stock.xls");
		service.exportExcel(fos, pager, null, null, null);
	}

}
