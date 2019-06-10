package com.icss.oa.meeting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingEmp;
import com.icss.oa.system.pojo.Employee;

public interface MeetingEmpMapper {
	void delete(Integer meetingEmpId);// 删除记录

	void insert(MeetingEmp meetingEmp);// 插入记录
	
	MeetingEmp queryById(Integer meetingEmpId);// 查询参会记录
	
	List<MeetingEmp> queryByCondition(@Param("start") Integer start, 
	                                  @Param("pageSize") Integer pageSize,
			                          @Param("meetingEmpId") Integer meetingEmpId, 
			                          @Param("meetingId") Integer meetingId, 
			                          @Param("empId") Integer empId);//动态查询参会人员

	List<MeetingEmp> query();//查询所有参会记录
	
	Integer getMeetingEmpCount();//查询数据数量
	
	Integer getMeetingEmpCountCondition(@Param("meetingEmpId") Integer meetingEmpId, 
                                        @Param("meetingId") Integer meetingId, 
                                        @Param("empId") Integer empId);//条件查询数据数量
	
	List<MeetingEmp> queryByPage(@Param("start") Integer start,                         
                                 @Param("pageSize") Integer pageSize);// 分页查询数据

}