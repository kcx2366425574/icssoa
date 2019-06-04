package com.icss.oa.system.pojo;

import java.util.Date;

public class Sign {
	
	private int signId;
	private Employee emp;
	private Date signTime;
	
	public Sign() {
		// TODO Auto-generated constructor stub
	}
	public Sign(Employee emp, Date SignTime) {
		super();
		this.emp = emp;
		this.signTime = SignTime;
	}
	public Sign(int SignId, Employee emp, Date SignTime) {
		super();
		this.signId = SignId;
		this.emp = emp;
		this.signTime = SignTime;
	}
	
	public int getSignId() {
		return signId;
	}
	public void setSignId(int SignId) {
		this.signId = SignId;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime( Date signTime) {
		this.signTime = signTime;
	}
	@Override
	public String toString() {
		return "Sign [signId=" + signId + ", emp=" + emp + ", signTime=" + signTime + "]";
	}
	
	
	
	
}
