package com.icss.oa.system.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icss.oa.system.dao.CheckMapper;
import com.icss.oa.system.pojo.Check;
import com.icss.oa.system.pojo.Employee;

@Service
@Transactional(rollbackFor=Exception.class)
public class CheckService {
	@Autowired
	private CheckMapper mapper;
	/*
	 * 签到
	 */
	
	public void addCheck(Check check){
		mapper.insert(check);
	}
	/*
	 * 查询所有签到记录
	 */
	@Transactional(readOnly=true)
	public List<Check> queryCheck(int start,int pageSize){
		return mapper.query(start,pageSize);
	}
	/*
	 * 员工id查询签到记录
	 */
	@Transactional(readOnly=true)
	public List<Check> queryByEmpId(int empid,int start,int pagesize){
		return mapper.queryByEmpId(empid,start,pagesize);
	}
	public int getCheckCount() {
		return mapper.getCount();
	}
	public int getCheckEmpCount() {
		return mapper.getECount();
	}
	
}
