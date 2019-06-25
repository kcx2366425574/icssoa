package com.icss.oa.analyse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.analyse.dao.StockAnalyseMapper;

/**
 * 数据分析业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class StockAnalyseService {
	
	@Autowired
	private StockAnalyseMapper mapper;
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> query() {
		return mapper.query();
	}

}
