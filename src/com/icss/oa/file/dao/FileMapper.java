package com.icss.oa.file.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.file.pojo.File;




public interface FileMapper {
    
    void insert(File record);
    
    void delete(Integer fileId);
    
    void update(File record);
    
    File queryById(Integer fileId);

    List<File> query();
    
    List<File> queryByPage(@Param("start")Integer start,@Param("pageSize")Integer pageSize);
    
    int getCount();
    
    List<File> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("folId") Integer folId,@Param("fileName") String fileName);
    
    int getCountByCondition(@Param("folId") Integer folId,@Param("fileName") String fileName); 
	
	List<File> queryByIds(@Param("ids") Integer[] ids);
}