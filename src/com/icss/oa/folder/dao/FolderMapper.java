package com.icss.oa.folder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.folder.pojo.Folder;

public interface FolderMapper {
	
    int delete(Integer folId);

    int insert(Folder record);
    
    void update(Folder record);

    
    List<Folder> queryByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
    
    int getCount();
    
    Folder queryById(Integer folId);

    
}