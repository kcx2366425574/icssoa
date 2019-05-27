package com.icss.oa.system.service;

import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.CheckMapper;
import com.icss.oa.system.pojo.Check;

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
	public List<Check> query(int start,int pageSize){
		return mapper.query(start,pageSize);
	}
	/*
	 * id查询签到记录
	 */
	@Transactional(readOnly=true)
	public List<Check> queryById(int id){
		return mapper.queryById(id);
	}
	
}
