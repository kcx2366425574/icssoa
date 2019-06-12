package com.icss.oa.meeting.controller;

import java.util.Arrays;
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
import com.icss.oa.meeting.pojo.MeetingEmp;
import com.icss.oa.meeting.service.MeetingEmpService;
import com.icss.oa.meeting.service.MeetingService;

/**
 * 参会人员控制
 * 
 * @author Administrator
 *
 */
@Controller
public class MeetingEmpController {
	@Autowired
	private MeetingEmpService service;

	// 增加参会人员
	@RequestMapping("/meetingEmp/add")
	public void add(HttpServletRequest request, HttpServletResponse response, MeetingEmp meetingemp) {
		service.addMeetingEmp(meetingemp);

	}

	// 删除参会人员
	@RequestMapping(" /meetingEmp/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer meetingEmpId) {
		System.out.println("删除参会人员");
		service.deleteMeetingEmp(meetingEmpId);
	}

	// 分页查询参会人员
	@RequestMapping("/meetingEmp/queryByPage")
	@ResponseBody
	public HashMap<String, Object> queryByPage(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize) {
		
		if (pageNum == null)
			pageNum = 0;

		if (pageSize == null)
			pageSize = 10;

		Pager pager = new Pager(service.getMeetingEmpCount(), pageSize, pageNum);
		List<MeetingEmp> list = service.queryMeetingEmpByPage(pageNum, pageSize);

		// 在MAP集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;

	}

	// 分页模糊查询
	@RequestMapping("/meetingEmp/queryByCondition")
	@ResponseBody
	public HashMap<String, Object> queryByCondition(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize, Integer meetingEmpId, Integer meetingId, Integer empId) {

		if (pageNum == null)
			pageNum = 1;

		if (pageSize == null)
			pageSize = 10;

		Pager pager = new Pager(service.getMeetingEmpCountCondition(meetingEmpId, meetingId, empId), pageSize, pageNum);
		List<MeetingEmp> list = service.queryByCondition(pager, meetingEmpId, meetingId, empId);

		// 在MAP集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;

	}
	
	    //批量删除参会人员
			@RequestMapping("/meetingEmp/deleteMany")
			public void deleteMany(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
				System.out.println("删除参会人员");
				System.out.println(Arrays.toString(ids));
//				for(Integer meetingEmpId:meetingEmpIds){
//					System.out.println(meetingEmpId);
//				//service.deleteMeeting(meetingId);
//				}
			}

}
