package com.icss.oa.reb.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.icss.oa.system.pojo.Employee;

public class Reb {
	private int rebId;
	private String rebReason;
	private int rebAmount;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date rebTime;
	private String rebApprovalStatus;
	private Employee emp;

	public Reb() {
		super();
	}
	public Reb(int rebId, String rebReason, int rebAmount, Date rebTime, String rebApprovalStatus, Employee emp) {
		super();
		this.rebId = rebId;
		this.rebReason = rebReason;
		this.rebAmount = rebAmount;
		this.rebTime = rebTime;
		this.rebApprovalStatus = rebApprovalStatus;
		this.emp = emp;

	}
	public Reb(String rebReason, int rebAmount, Date rebTime, String rebApprovalStatus, Employee emp) {
		super();
		this.rebReason = rebReason;
		this.rebAmount = rebAmount;
		this.rebTime = rebTime;
		this.rebApprovalStatus = rebApprovalStatus;
		this.emp = emp;

	}
	public int getRebId() {
		return rebId;
	}
	public void setRebId(int rebId) {
		this.rebId = rebId;
	}
	public String getRebReason() {
		return rebReason;
	}
	public void setRebReason(String rebReason) {
		this.rebReason = rebReason;
	}
	public int getRebAmount() {
		return rebAmount;
	}
	public void setRebAmount(int rebAmount) {
		this.rebAmount = rebAmount;
	}
	public Date getRebTime() {
		return rebTime;
	}
	public void setRebTime(Date rebTime) {
		this.rebTime = rebTime;
	}
	public String getRebApprovalStatus() {
		return rebApprovalStatus;
	}
	public void setRebApprovalStatus(String rebApprovalStatus) {
		this.rebApprovalStatus = rebApprovalStatus;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}



}
