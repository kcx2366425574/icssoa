package com.icss.oa.system.pojo;

public class Check {
	
	private int checkId;
	private int checkEmpId;
	private String checkTime;
	public Check() {
		// TODO Auto-generated constructor stub
	}
	public Check(int checkId, int checkEmpId, String checkTime) {
		super();
		this.checkId = checkId;
		this.checkEmpId = checkEmpId;
		this.checkTime = checkTime;
	}
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public int getCheckEmpId() {
		return checkEmpId;
	}
	public void setCheckEmpId(int checkEmpId) {
		this.checkEmpId = checkEmpId;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	

}
