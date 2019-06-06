package com.icss.oa.system.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.SignMapper;
import com.icss.oa.system.pojo.Sign;

import sun.print.resources.serviceui;

@Service
public class SignService {

	@Autowired
	private SignMapper mapper;
	
	//增加签到记录
	public void insert(Integer signEmpId,String signTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date date = sdf.parse(signTime);
		mapper.insert(signEmpId, date);
	}
	
	//查询签到记录
	public List<Sign> query(Integer signEmpId,Pager pager){
		return mapper.query(signEmpId, pager.getStart(), pager.getPageSize());
	}
	
	//查询签到记录数
	public int getCount(Integer signEmpId){
		return mapper.getAllCount(signEmpId);
	}
	
}
