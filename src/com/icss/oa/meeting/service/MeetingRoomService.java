package com.icss.oa.meeting.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.icss.oa.meeting.dao.MeetingRoomMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;


/**
 * 会议室业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)//加入事物
public class MeetingRoomService {
	
	@Autowired
	private MeetingRoomMapper mapper;
	
	//增加会议室
	
	public void addMeetingRoom(MeetingRoom meetingRoom){
		mapper.insert(meetingRoom);
		
	}
	
	/**
	 * 修改会议室
	 */
	public void updateMeetingRoom(MeetingRoom meetingRoom) {
		mapper.update(meetingRoom);
	}

	/**
	 * 删除会议室
	 */
	public void deleteMeetingRoom(Integer meetingRoomId) {
		mapper.delete(meetingRoomId);
	}
	
	/**
	 * 通过id查询会议室
	 */
	@Transactional(readOnly=true)
	public MeetingRoom queryMeetingRoomById(Integer meetingRoomId) {
		return mapper.queryById(meetingRoomId);
	}
	
	/**
	 * 查询所有会议室
	 */
	@Transactional(readOnly=true)
	public List<MeetingRoom> queryMeetingRoom() {
		return mapper.query();
	}
	
	/**
	 * 条件查询会议室
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MeetingRoom> queryByCondition(Integer start,Integer pageSize,Integer meetingRoomId,String meetingRoomName,String meetingRoomState,String meetingRoomLocation,String meetingRoomCondition,Integer meetingRoomSize) {
		return mapper.queryByCondition(start, pageSize, meetingRoomId, meetingRoomName, meetingRoomState, meetingRoomLocation, meetingRoomCondition, meetingRoomSize);
	}
	
	/**
	 * 获得会议室数量
	 * @return
	 */
	public int getMeetingRoomCount() {
		return mapper.getMeetingRoomCount();
	}
	
	/**
	 * 分页查询所有会议室
	 */
	@Transactional(readOnly=true)
	public List<MeetingRoom> queryMeetingRoomByPage(Integer start,Integer pageSize) {
		return mapper.queryByPage2(start,pageSize);
	}

}