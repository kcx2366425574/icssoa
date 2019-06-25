package com.icss.oa.folder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.folder.pojo.Files;

public interface FilesMapper {
    
    void insert(Files files);

	void delete(Integer fileId);
	
	void deleteMany(@Param("ids")Integer[] ids);
	
	void update(Files files);
	
	List<Files> query();
	
	List<Files> queryByFolder(@Param("folId") Integer folId);

    Files queryById(Integer fileId);
    
    List<Files> queryByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
    
    List<Files> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("folId") Integer folId);

    int getCount();
    
    int getCountByCondition(@Param("folId") Integer folId);
    
    List<Files> queryByIds(@Param("ids") Integer[] ids);
}