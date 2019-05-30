package com.icss.oa.meeting.pojo;
/**
 * 会议实体类
 * @author Administrator
 *
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.icss.oa.system.pojo.Employee;

public class Meeting {
	private Integer meetingId;//会议id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;//会议开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;//会议结束时间
	private Employee promoter;//会议发起人
	private String meetingTheme;//会议主题
	private String meetingState;//会议状态：已经审批、未审批、已经结束
	private MeetingRoom meetingRoom;//会议所在会议室
	
	public Meeting() {
		super();
		// TODO Auto-generated constructor stub
	}//无参构造方法

	public Meeting(Integer meetingId, Date startTime, Date endTime, Employee promoter, String meetingTheme,
			String meetingState, MeetingRoom meetingRoom) {
		super();
		this.meetingId = meetingId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.promoter = promoter;
		this.meetingTheme = meetingTheme;
		this.meetingState = meetingState;
		this.meetingRoom = meetingRoom;
	}
	

	public Meeting(Date startTime, Date endTime, Employee promoter, String meetingTheme, String meetingState,
			MeetingRoom meetingRoom) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.promoter = promoter;
		this.meetingTheme = meetingTheme;
		this.meetingState = meetingState;
		this.meetingRoom = meetingRoom;
	}//不包含会议编号的构造方法

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Employee getPromoter() {
		return promoter;
	}

	public void setPromoter(Employee promoter) {
		this.promoter = promoter;
	}

	public String getMeetingTheme() {
		return meetingTheme;
	}

	public void setMeetingTheme(String meetingTheme) {
		this.meetingTheme = meetingTheme;
	}

	public String getMeetingState() {
		return meetingState;
	}

	public void setMeetingState(String meetingState) {
		this.meetingState = meetingState;
	}

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	@Override
	public String toString() {
		return "Meeting [meetingId=" + meetingId + ", startTime=" + startTime + ", endTime=" + endTime + ", promoter="
				+ promoter + ", meetingTheme=" + meetingTheme + ", meetingState=" + meetingState + ", meetingRoom="
				+ meetingRoom + "]";
	}
	

	

}
