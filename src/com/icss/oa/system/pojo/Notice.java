package com.icss.oa.system.pojo;

public class Notice {
	
	private int noticeId;
	private String noticeName;
	private String noticeInfo;
	private Employee emp;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	

	public Notice(int noticeId, String noticeName, String noticeInfo, Employee emp) {
		super();
		this.noticeId = noticeId;
		this.noticeName = noticeName;
		this.noticeInfo = noticeInfo;
		this.emp = emp;
	}


	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	
	
	

}
