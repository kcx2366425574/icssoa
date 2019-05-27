package com.icss.oa.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.system.pojo.Job;


public interface JobMapper {
	
	void insert(Job job);
	
	void update(Job job);
	
	void delete(Integer jobId);
	
	Job queryById(Integer jobId);
	
	List<Job> query(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
    
}