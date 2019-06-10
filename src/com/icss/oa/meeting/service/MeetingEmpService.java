package com.icss.oa.meeting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.dao.MeetingEmpMapper;
import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingEmp;



/**
 * 参会人员业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)//加入事物
public class MeetingEmpService {
	
	@Autowired
	private MeetingEmpMapper mapper;
	
	//增加参会记录
	
	public void addMeetingEmp(MeetingEmp meetingEmp){
		mapper.insert(meetingEmp);
		
	}
	
	

	/**
	 * 删除参会记录
	 */
	public void deleteMeetingEmp(Integer meetingEmpId) {
		mapper.delete(meetingEmpId);
	}
	
	/**
	 * 通过id查询参会记录
	 */
	@Transactional(readOnly=true)
	public MeetingEmp queryMeetingEmpById(Integer meetingId) {
		return mapper.queryById(meetingId);
	}
	
	/**
	 * 查询所有参会记录
	 */
	@Transactional(readOnly=true)
	public List<MeetingEmp> queryMeeting() {
		return mapper.query();
	}
	
	/**
	 * 条件查询参会记录
	 */
	@Transactional(readOnly=true)
	public List<MeetingEmp> queryByCondition(Pager pager,Integer meetingEmpId,Integer meetingId,Integer empId) {
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), meetingEmpId, meetingId, empId);
	}
	
	/**
	 * 分页查询所有参会记录
	 */
	@Transactional(readOnly=true)
	public List<MeetingEmp> queryMeetingEmpByPage(Integer start,Integer pageSize) {
		return mapper.queryByPage(start,pageSize);
	}
	
	/**
	 * 查询数据数量
	 * @return 
	 */
	@Transactional(readOnly=true)
	public int getMeetingEmpCount() {
		return mapper.getMeetingEmpCount();
	}
	
	/**
	 * 条件查询数据数量
	 * @return 
	 */
	@Transactional(readOnly=true)
	public int getMeetingEmpCountCondition(Integer meetingEmpId,Integer meetingId,Integer empId) {
		return mapper.getMeetingEmpCountCondition(meetingEmpId, meetingId, empId);
	}
	
	
}