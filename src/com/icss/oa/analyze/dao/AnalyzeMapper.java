package com.icss.oa.analyze.dao;

import java.util.List;
import java.util.Map;

import com.icss.oa.analyze.dto.DeptEmpCount;

public interface AnalyzeMapper {
	
	//ͳ��ÿ�����ŵ�Ա������
	List<DeptEmpCount> queryDeptEmpCount();
	
	//ͳ��ÿ��������ߺ���͹���
	List<Map<String, Object>> querySalMinMax();
	
	//ͳ��ÿ��ְ���Ա������
	List<Map<String, Object>> queryJobEmpCount();

}