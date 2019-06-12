package com.icss.oa.meeting.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.pojo.Employee;

public interface MeetingMapper {

	void insert(Meeting meeting);// 增加新会议

	void delete(Integer meetingId);// 根据会议编号删除会议

	void update(Meeting meeting);// 修改会议信息
	
	void updateMeetingState(@Param("meetingId") Integer meetingId, 
                            @Param("meetingState") String meetingState);//修改会议室状态

	Meeting queryById(Integer meetingId);// 根据会议编号查询会议
	
	Integer getMeetingCount();//查询会议数据数量
	
	Integer getMeetingConditionCount(    @Param("meetingId") Integer meetingId,                 /**根据ID查询**/
			                             @Param("empId") Integer empId,                         /**根据ID查询**/
                                         @Param("empName") String empName,                      /**根据会议发起人姓名查询**/
                                         @Param("meetingState") String meetingState,            /**根据会议状态查询**/
                                         @Param("startTime") String startTime,                  /**根据会议时间查询**/
                                         @Param("meetingTheme") String meetingTheme,            /**根据会议主题查询**/
                                         @Param("meetingRoomName") String meetRoomName,         /**根据会议室名字查询**/
                                         @Param("meetingRoomLocation") String meetRoomLocation  /**根据会议室位置查询**/);//查询符合条件的会议室数量

	List<Meeting> query();// 查询多条会议数据
	
	List<Meeting> queryByPage2(@Param("start") Integer start,                         /**开始的条数**/
                               @Param("pageSize") Integer pageSize);// 分页查询会议数据

	List<Meeting> queryByPage(HashMap<String, Integer> map);// 分页查询会议数据
	
	//动态查询数据
	List<Meeting> queryByCondition(@Param("start") Integer start,                         /**开始的条数**/
			                       @Param("pageSize") Integer pageSize,                   /**结束的条数**/
			                       @Param("meetingId") Integer meetingId,                 /**根据ID查询**/
			                       @Param("empId") Integer empId,                         /**根据ID查询**/
			                       @Param("empName") String empName,                      /**根据会议发起人姓名查询**/
			                       @Param("meetingState") String meetingState,            /**根据会议状态查询**/
			                       @Param("startTime") String startTime,                  /**根据会议时间查询**/
			                       @Param("meetingTheme") String meetingTheme,            /**根据会议主题查询**/
			                       @Param("meetingRoomName") String meetRoomName,         /**根据会议室名字查询**/
			                       @Param("meetingRoomLocation") String meetRoomLocation  /**根据会议室位置查询**/);
	
	int getLastInsertId();
}