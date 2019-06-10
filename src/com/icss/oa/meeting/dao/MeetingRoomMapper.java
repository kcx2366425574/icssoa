package com.icss.oa.meeting.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;

public interface MeetingRoomMapper {

	void insert(MeetingRoom meetingRoom);// 新增会议室

	void delete(Integer meetingRoomId);// 通过ID删除会议室

	MeetingRoom queryById(Integer meetingRoomId);// 根据会议室编号查询会议室

	void update(MeetingRoom meetingRoom);// 修改会议室信息
	
	void updateMeetingRoomState(@Param("meetingRoomId") Integer meetingRoomId, 
                                @Param("meetingRoomState") String meetingRoomState);//修改会议室状态

	List<MeetingRoom> query();// 查询多条数据
	
	Integer getMeetingRoomCount();//查询会议室数量
	
	Integer getMeetingRoomConditionCount(@Param("meetingRoomId") Integer meetingRoomId,               /**根据会议室编号查询**/
                                         @Param("meetingRoomName") String meetingRoomName,            /**根据会议室名字模糊查询**/
                                         @Param("meetingRoomState") String meetingRoomState,          /**根据会议室状态模糊查询**/
                                         @Param("meetingRoomLocation") String meetingRoomLocation,    /**根据会议室位置模糊查询**/
                                         @Param("meetingRoomCondition") String meetingRoomCondition,  /**根据会议室有无空调模糊查询**/
                                         @Param("meetingRoomSize") Integer meetingRoomSize            /**根据会议室大小查询**/);//查询符合条件的会议室数量
	
	List<MeetingRoom> queryByPage2(@Param("start") Integer start,                         /**开始的条数**/
                                   @Param("pageSize") Integer pageSize);                   // 分页查询会议室数据

	List<MeetingRoom> queryByPage(HashMap<String, Integer> map);// 分页查询
 
	// 动态查询数据
	List<MeetingRoom> queryByCondition(@Param("start") Integer start, 
			                           @Param("pageSize") Integer pageSize,
			                           @Param("meetingRoomId") Integer meetingRoomId,               /**根据会议室编号查询**/
			                           @Param("meetingRoomName") String meetingRoomName,            /**根据会议室名字模糊查询**/
			                           @Param("meetingRoomState") String meetingRoomState,          /**根据会议室状态模糊查询**/
			                           @Param("meetingRoomLocation") String meetingRoomLocation,    /**根据会议室位置模糊查询**/
			                           @Param("meetingRoomCondition") String meetingRoomCondition,  /**根据会议室有无空调模糊查询**/
			                           @Param("meetingRoomSize") Integer meetingRoomSize            /**根据会议室大小查询**/);
	int getLastInsertId();
	
	
}