package com.icss.oa.analyze.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.analyze.dao.AnalyzeMapper;
import com.icss.oa.analyze.dto.DeptEmpCount;

/**
 * 数据分析业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AnalyzeService {
	
	@Autowired
	private AnalyzeMapper mapper;
	
	@Transactional(readOnly=true)
	public List<DeptEmpCount> queryDeptEmpCount() {
		
		return mapper.queryDeptEmpCount();
	}
	
	@Transactional(readOnly=true)
	public List<Map<String,Object>> querySalMinMax() {
		
		return mapper.querySalMinMax();
	}
	
	@Transactional(readOnly=true)
	public List<Map<String,Object>> queryJobEmpCount() {
		
		return mapper.queryJobEmpCount();
	}

}