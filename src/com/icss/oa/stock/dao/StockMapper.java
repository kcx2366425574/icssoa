package com.icss.oa.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.stock.pojo.Stock;

public interface StockMapper {
	
	void insert(Stock stock);
	
	void delete(Integer stockId);
	
	void update(Stock stock);
	
	Stock queryById(Integer stockId);
	
	List<Stock> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("stockName") String stockName, @Param("stockTime") String stockTime);
	
	int getCountByCondition(@Param("stockName") String stockName, @Param("stockTime") String stockTime);
    
}