package com.icss.oa.meeting.pojo;

import com.icss.oa.system.pojo.Employee;

public class MeetingEmp {
    private Integer meetingEmpId;//编号

    private Meeting meeting;//会议

    private Employee emp;//参会人员

	public MeetingEmp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MeetingEmp(Integer meetingEmpId, Meeting meeting, Employee emp) {
		super();
		this.meetingEmpId = meetingEmpId;
		this.meeting = meeting;
		this.emp = emp;
	}

	public MeetingEmp(Meeting meeting, Employee emp) {
		super();
		this.meeting = meeting;
		this.emp = emp;
	}

	public Integer getMeetingEmpId() {
		return meetingEmpId;
	}

	public void setMeetingEmpId(Integer meetingEmpId) {
		this.meetingEmpId = meetingEmpId;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "MeetingEmp [meetingEmpId=" + meetingEmpId + ", meeting=" + meeting + ", emp=" + emp + "]";
	}
    

    
}