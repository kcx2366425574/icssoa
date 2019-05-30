package com.icss.oa.folder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.folder.pojo.Files;

public interface FilesMapper {
    
    void insert(Files files);

	void delete(Integer fileId);
	
	void update(Files files);

    Files queryById(Integer fileId);
    
    List<Files> queryByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
    
    List<Files> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("folId") Integer folId,@Param("fileName")String fileName);

    int getCount();
    
    int getCountByCondition(@Param("folId") Integer folId,@Param("fileName")String fileName);
    
    List<Files> queryByIds(@Param("ids") Integer[] ids);
}