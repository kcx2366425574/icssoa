package com.icss.oa.folder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.schedule.pojo.Schedule;

public interface FolderMapper {
	
    int delete(Integer folId);

    int insert(Folder record);
    
    void update(Folder record);

    List<Folder> query(@Param("empId") Integer empId);
    
    List<Folder> queryAll();
    
    List<Folder> queryByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
    
    int getCount();
    
    Folder queryById(Integer folId);
    
    List<Folder> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("empId") Integer empId,@Param("folName") String folName);

    int getCountByCondition(@Param("empId") Integer empId,@Param("folName") String folName);
    
    List<Folder> queryByIds(@Param("ids") Integer[] ids);
}