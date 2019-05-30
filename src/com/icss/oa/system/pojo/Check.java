package com.icss.oa.system.pojo;

import java.util.Date;

public class Check {
	
	private int checkId;
	private Employee emp;
	private Date checkTime;
	public Check() {
		// TODO Auto-generated constructor stub
	}
	public Check(Employee emp, Date checkTime) {
		super();
		this.emp = emp;
		this.checkTime = checkTime;
	}
	public Check(int checkId, Employee emp, Date checkTime) {
		super();
		this.checkId = checkId;
		this.emp = emp;
		this.checkTime = checkTime;
	}
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	@Override
	public String toString() {
		return "check [checkId=" + checkId +  ",checkTime="+checkTime.toString()+"]";
	}
	
}
