package com.icss.oa.folder.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.folder.dao.FilesMapper;
import com.icss.oa.folder.pojo.Files;


@Service
@Transactional(rollbackFor=Exception.class)

public class FilesService {
	
	@Autowired
	private FilesMapper mapper;
	
	public void addFiles(Files files)
	{
		mapper.insert(files);
	}
	
	public void deleteFiles(Integer fileId)
	{
		mapper.delete(fileId);
	}
	
	public void updateFiles(Files files)
	{
		mapper.update(files);
	}
	
	@Transactional(readOnly=true)
	public Files queryFileById(Integer fileId)
	{
		return mapper.queryById(fileId);
	}
	
	@Transactional(readOnly=true)
	public List<Files> queryFileByPage(Pager pager)
	{
		return mapper.queryByPage(pager.getStart(), pager.getPageSize());
	}
	
	public int getCount()
	{
		return mapper.getCount();
	}
	
	@Transactional(readOnly=true)
	public List<Files> queryByConditon(Pager pager,@Param("folId") Integer folId,@Param("fileName") String fileName)
	{
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(),folId,  fileName);
	}
	
	public int getCountByCondition(@Param("folId") Integer folId,@Param("fileName") String fileName)
	{
		return mapper.getCountByCondition(folId,fileName);
	}
	
	public List<Files> queryByIds(@Param("ids") Integer[] ids)
	{
		return mapper.queryByIds(ids);
	}
}
