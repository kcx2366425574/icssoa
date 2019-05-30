package com.icss.oa.meeting.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingRoomService;
import com.icss.oa.system.pojo.Department;

/**
 * 会议室控制器
 * 
 * @author Administrator
 *
 */

@Controller
public class MeetingRoomController {
	@Autowired
	private MeetingRoomService service;

	// 增加会议室
	@RequestMapping(" /meetingRoom/add")
	public void add(HttpServletRequest request, HttpServletResponse response, MeetingRoom meetingRoom) {
		System.out.println("增加会议室");
		service.addMeetingRoom(meetingRoom);
	}

	// 删除会议室
	@RequestMapping(" /meetingRoom/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer meetingRoomId) {
		System.out.println("删除会议室");
		service.deleteMeetingRoom(meetingRoomId);
	}

	// 查询会议室
	@RequestMapping(" /meetingRoom/query")
	@ResponseBody
	public List<MeetingRoom> query(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("查詢会议室");
		return service.queryMeetingRoom();
	}
	
	// 查询会议室
		@RequestMapping(" /meetingRoom/queryById")
		@ResponseBody
		public MeetingRoom queryById(HttpServletRequest request, HttpServletResponse response,Integer meetingRoomId) {
			System.out.println("查詢会议室");
			return service.queryMeetingRoomById(meetingRoomId);
		}

	// 修改会议室
	@RequestMapping(" /meetingRoom/update")
	public void update(HttpServletRequest request, HttpServletResponse response, MeetingRoom meetingRoom) {
		System.out.println("修改会议室");
		service.updateMeetingRoom(meetingRoom);
	}

	// 分页查询会议室
	@RequestMapping("/meetingRoom/queryByPage")
	@ResponseBody
	public HashMap<String, Object> queryByPage(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize) {

		if (pageNum == null)
			pageNum = 0;

		if (pageSize == null)
			pageSize = 10;

		Pager pager = new Pager(service.getMeetingRoomCount(), pageSize, pageNum);
		List<MeetingRoom> list = service.queryMeetingRoomByPage(pageNum, pageSize);

		// 在MAP集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;

	}

	// 分页模糊查询
	@RequestMapping("/meetingRoom/queryByCondition")
	@ResponseBody
	public HashMap<String, Object> queryByCondition(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize, Integer meetingRoomId, String meetingRoomName, String meetingRoomState,
			String meetingRoomLocation, String meetingRoomCondition, Integer meetingRoomSize) {
		
		if (pageNum == null)
			pageNum = 0;

		if (pageSize == null)
			pageSize = 10;

		Pager pager = new Pager(service.getMeetingRoomCount(), pageSize, pageNum);
		List<MeetingRoom> list = service.queryByCondition(pageNum, pageSize, meetingRoomId, meetingRoomName,
				meetingRoomState, meetingRoomLocation, meetingRoomCondition, meetingRoomSize);

		// 在MAP集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;

	}

}
