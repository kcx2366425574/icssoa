package com.icss.oa.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.stock.pojo.StockRecord;

public interface StockRecordMapper {
    
	void insert(StockRecord stockRecord);
	
	void delete(Integer srId);
	
	void update(StockRecord stockRecord);
	
	StockRecord queryById(Integer srId);
	
	int getCount(@Param("srDeptId") Integer srDeptId, @Param("srStockId") Integer srStockId, 
			@Param("srTime") String srTime);
	
	List<StockRecord> queryByCond(@Param("start") Integer start, @Param("pageSize") Integer pageSize, 
			@Param("srDeptId") Integer srDeptId, @Param("srStockId") Integer srStockId, 
			@Param("srTime") String srTime);
	
}