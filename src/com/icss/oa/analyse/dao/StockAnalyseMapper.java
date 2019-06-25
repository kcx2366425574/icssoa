package com.icss.oa.analyse.dao;

import java.util.List;
import java.util.Map;

public interface StockAnalyseMapper {
	
	//统计每个物品全部状态数据
	List<Map<String, Object>> query();

}
