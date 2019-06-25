package com.icss.oa.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.stock.dao.StockMapper;
import com.icss.oa.stock.pojo.Stock;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockService { 
	
	@Autowired
	private StockMapper mapper;
	
	//增加数据
	public void addStock(Stock stock) {
		mapper.insert(stock);
	}
	
	//删除数据
	public void deleteStock(Integer stockId) {
		mapper.delete(stockId);
	}
	
	//修改数据
	public void updateStock(Stock stock) {
		mapper.update(stock);
	}
	
	//根据id查询数据
	@Transactional(readOnly = true)
	public Stock queryStockById(Integer stockId) {
		return mapper.queryById(stockId);
	}
	
	//条件获取记录数
	@Transactional(readOnly = true)
	public Integer getStockCountBuCondition(String stockName, String stockTime) {
		return mapper.getCountByCondition(stockName, stockTime);
	}
	
	//条件查询
	@Transactional(readOnly = true)
	public List<Stock> queryStockByCondition(Pager pager, String stockName, String stockTime) {
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), stockName, stockTime);
	}
	

}
