package com.icss.oa.schedule.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.folder.pojo.Files;
import com.icss.oa.schedule.pojo.Schedule;
import com.icss.oa.system.pojo.Employee;

public interface ScheduleMapper {

    void insert(Schedule record);

    void delete(Integer schId);
    
    void insertSelective(Schedule record);
    
    void update(Schedule record);

    Schedule queryById(Integer schId);
    
    List<Schedule> queryByPage(@Param("start")Integer start,@Param("pageSize")Integer pageSize);
    
    List<Schedule> queryByCondition(@Param("start")Integer start,@Param("pageSize")Integer pageSize,@Param("empId")Integer e1,@Param("empId")Integer e2,@Param("schName")String schName);
    
    List<Schedule> queryByIds(@Param("ids") Integer[] ids);
    
    int getCount();
    
    int getCountByCondition(@Param("empId") Integer e1,@Param("empId") Integer e2,@Param("schName")String schName);
    
    

}