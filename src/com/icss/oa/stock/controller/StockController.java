package com.icss.oa.stock.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.stock.pojo.Stock;
import com.icss.oa.stock.service.StockService;

@Controller
public class StockController {
	
	@Autowired
	private StockService service;
	
	//增加库存
	@RequestMapping("/stock/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Stock stock) {
		service.addStock(stock);
	}
	
	//删除库存
	@RequestMapping("/stock/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer stockId) {
		service.deleteStock(stockId);
	}
	
	//修改库存
	@RequestMapping("/stock/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Stock stock) {
		service.updateStock(stock);
	}
	
	//根据id查询库存
	@RequestMapping("/stock/queryById")
	@ResponseBody
	public Stock queryById(HttpServletRequest request, HttpServletResponse response, Integer stockId) {
		return service.queryStockById(stockId);
	}
	
	//条件查询
	@RequestMapping("/stock/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request, HttpServletResponse response, Integer pageSize,
			Integer pageNum, String stockName, String stockTime) {

		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = service.getStockCountBuCondition(stockName, stockTime);
		
		Pager pager = new Pager(service.getStockCountBuCondition(stockName, stockTime), pageSize, pageNum);
		List<Stock> list = service.queryStockByCondition(pager, stockName, stockTime);

		// 在Map集合中存储分页数据和库存数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

}
