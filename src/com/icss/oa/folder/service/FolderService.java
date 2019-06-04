package com.icss.oa.folder.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.folder.dao.FolderMapper;
import com.icss.oa.folder.pojo.Folder;

@Service
@Transactional(rollbackFor=Exception.class)
public class FolderService {
	@Autowired
	private FolderMapper mapper;
	public void addFolder(Folder folder)
	{
		mapper.insert(folder);
	}
	public void deleteFolder(Integer folId)
	{
		mapper.delete(folId);
	}
	public void updateFolder(Folder folder)
	{
		mapper.update(folder);
	}
	@Transactional(readOnly=true)
	public List<Folder> query()
	{
		return mapper.query();
	}
	@Transactional(readOnly=true)
	public Folder queryFolderById(Integer folId)
	{
		return mapper.queryById(folId);
	}
	@Transactional(readOnly=true)
	public List<Folder> queryFolderByPage(Pager pager)
	{
		return mapper.queryByPage(pager.getStart(), pager.getPageSize());
	}
	public int getCount()
	{
		return mapper.getCount();
	}
	@Transactional(readOnly=true)
	public List<Folder> queryByConditon(Pager pager,@Param("folId") Integer folId,@Param("empId") Integer empId,@Param("folName") String folName)
	{
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(),folId, empId, folName);
	}
	public int getCountByCondition(@Param("folId") Integer folId,@Param("empId") Integer empId,@Param("folName") String folName)
	{
		return mapper.getCountByCondition(folId,empId, folName);
	}
	public List<Folder> queryByIds(@Param("ids") Integer[] ids)
	{
		return mapper.queryByIds(ids);
	}
}
