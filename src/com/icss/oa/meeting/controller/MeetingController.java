package com.icss.oa.meeting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingService;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;

/**
 * 会议控制
 * 
 * @author Administrator
 *
 */
@Controller
public class MeetingController {
	@Autowired
	private MeetingService service;

	// 增加会议
	@RequestMapping("/meeting/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Meeting meeting) {
		service.addMeeting(meeting);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 延迟

	}

	// 删除会议
	@RequestMapping(" /meeting/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer meetingId) {
		service.deleteMeeting(meetingId);
	}

	// 修改会议
	@RequestMapping(" /meeting/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Meeting meeting) {
		service.updateMeeting(meeting);
	}

	// 分页查询会议
	@RequestMapping("/meeting/queryByPage")
	@ResponseBody
	public HashMap<String, Object> queryByPage(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize) {

		if (pageNum == null)
			pageNum = 1;

		if (pageSize == null)
			pageSize = 10;

		Pager pager = new Pager(service.getMeetingCount(), pageSize, pageNum);
		List<Meeting> list = service.queryMeetingByPage(pager);

		// 在MAP集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;

	}

	// 分页模糊查询
	@RequestMapping("/meeting/queryByCondition")
	@ResponseBody
	public HashMap<String, Object> queryByCondition(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize, Integer meetingId, Integer empId,String empName, String meetingState, String startTime,
			String meetingTheme, String meetingRoomName, String meetingRoomLocation) {
		
		if (pageNum == null)
			pageNum = 1;

		if (pageSize == null)
			pageSize = 10;

		Pager pager = new Pager(service.getMeetingConditionCount(meetingId,empId, empName, meetingState, startTime, meetingTheme, meetingRoomName, meetingRoomLocation), pageSize, pageNum);
		List<Meeting> list = service.queryMeetingByCondition(pager, meetingId,empId, empName, meetingState,
				startTime, meetingTheme, meetingRoomName, meetingRoomLocation);

		// 在MAP集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;

	}
	
	//批量删除会议
	// 删除会议
	@RequestMapping("/meeting/deleteMany")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer[] meetingIds) {
		for(Integer meetingId:meetingIds){
			service.deleteMeeting(meetingId);
		}
	}
		
	// 查询会议室
	@RequestMapping("/meeting/queryById")
	@ResponseBody
	public Meeting queryById(HttpServletRequest request, HttpServletResponse response,Integer meetingId) {
		return service.queryMeetingById(meetingId);
	}
				
	/**
	 * 全文检索会议
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@RequestMapping("meeting/queryByIndex")
	@ResponseBody
	public List<Meeting> queryByIndex(HttpServletRequest request,HttpServletResponse response,String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {
		
		return service.queryMeetingByIndex(queryStr);		
	}
}
