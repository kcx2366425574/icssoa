package com.icss.oa.meeting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.pojo.Meeting;


/**
 * 会议业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)//加入事物
public class MeetingService {
	
	@Autowired
	private MeetingMapper mapper;
	
	//增加会议
	
	public void addMeeting(Meeting meeting){
		mapper.insert(meeting);
		
	}
	
	/**
	 * 修改会议
	 */
	public void updateMeeting(Meeting meeting) {
		mapper.update(meeting);
	}

	/**
	 * 删除会议
	 */
	public void deleteMeeting(Integer meetingId) {
		mapper.delete(meetingId);
	}
	
	/**
	 * 查询会议数量
	 * @return 
	 */
	public int getMeetingCount() {
		return mapper.getMeetingCount();
	}
	
	/**
	 * 通过id查询会议
	 */
	@Transactional(readOnly=true)
	public Meeting queryMeetingById(Integer meetingId) {
		return mapper.queryById(meetingId);
	}
	
	/**
	 * 查询所有会议
	 */
	@Transactional(readOnly=true)
	public List<Meeting> queryMeeting() {
		return mapper.query();
	}
	
	/**
	 * 分页查询所有会议
	 */
	@Transactional(readOnly=true)
	public List<Meeting> queryMeetingByPage(Integer start,Integer pageSize) {
		return mapper.queryByPage2(start,pageSize);
	}
	
	/**
	 * 条件查询所有会议
	 */
	@Transactional(readOnly=true)
	public List<Meeting> queryMeetingByCondition(Integer start,Integer pageSize,Integer meetingId,String empName,String meetingState,String startTime,String meetingTheme, String meetRoomName,String meetingRoomLocation) {
		return mapper.queryByCondition(start, pageSize, meetingId, empName, meetingState, startTime, meetingTheme, meetRoomName, meetingRoomLocation);
	}

}
