package com.icss.oa.meeting.pojo;
/**
 * 会议室实体类
 * @author Administrator
 *
 */

public class MeetingRoom {
	private Integer meetingRoomId;//会议室编号
	private String meetingRoomName;//会议室名称
	private String meetingRoomState;//会议室状态：空闲、已有预约、已停用
	private String meetingRoomLocation;//会议室位置
	private int meetingRoomSize;//会议室容量
	private String meetingRoomCondition;//会议室是否有空调
	
	public MeetingRoom() {
		super();
		// TODO Auto-generated constructor stub
	}//无参构造方法

	public MeetingRoom(String meetingRoomName, String meetingRoomState, String meetingRoomLocation, int meetingRoomSize,
			String meetingRoomCondition) {
		super();
		this.meetingRoomName = meetingRoomName;
		this.meetingRoomState = meetingRoomState;
		this.meetingRoomLocation = meetingRoomLocation;
		this.meetingRoomSize = meetingRoomSize;
		this.meetingRoomCondition = meetingRoomCondition;
	}

	public MeetingRoom(Integer meetingRoomId, String meetingRoomName, String meetingRoomState,
			String meetingRoomLocation, int meetingRoomSize, String meetingRoomCondition) {
		super();
		this.meetingRoomId = meetingRoomId;
		this.meetingRoomName = meetingRoomName;
		this.meetingRoomState = meetingRoomState;
		this.meetingRoomLocation = meetingRoomLocation;
		this.meetingRoomSize = meetingRoomSize;
		this.meetingRoomCondition = meetingRoomCondition;
	}

	public Integer getMeetingRoomId() {
		return meetingRoomId;
	}

	public void setMeetingRoomId(Integer meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

	public String getMeetingRoomName() {
		return meetingRoomName;
	}

	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}

	public String getMeetingRoomState() {
		return meetingRoomState;
	}

	public void setMeetingRoomState(String meetingRoomState) {
		this.meetingRoomState = meetingRoomState;
	}

	public String getMeetingRoomLocation() {
		return meetingRoomLocation;
	}

	public void setMeetingRoomLocation(String meetingRoomLocation) {
		this.meetingRoomLocation = meetingRoomLocation;
	}

	public int getMeetingRoomSize() {
		return meetingRoomSize;
	}

	public void setMeetingRoomSize(int meetingRoomSize) {
		this.meetingRoomSize = meetingRoomSize;
	}

	public String getMeetingRoomCondition() {
		return meetingRoomCondition;
	}

	public void setMeetingRoomCondition(String meetingRoomCondition) {
		this.meetingRoomCondition = meetingRoomCondition;
	}

	@Override
	public String toString() {
		return "MeetingRoom [meetingRoomId=" + meetingRoomId + ", meetingRoomName=" + meetingRoomName
				+ ", meetingRoomState=" + meetingRoomState + ", meetingRoomLocation=" + meetingRoomLocation
				+ ", meetingRoomSize=" + meetingRoomSize + ", meetingRoomCondition=" + meetingRoomCondition + "]";
	}

	

}
