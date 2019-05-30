package com.icss.oa.schedule.pojo;

import java.sql.Date;

import com.icss.oa.system.pojo.Employee;

public class Schedule {
    private Integer schId;

    private String schName;

    private String schInfo;

    private Date schGrantDate;

    private Date schStartDate;

    private Date schEndDate;

    private Employee schGranter;

    private Employee schGrantee;

	public Integer getSchId() {
		return schId;
	}

	public void setSchId(Integer schId) {
		this.schId = schId;
	}

	public String getSchName() {
		return schName;
	}

	public void setSchName(String schName) {
		this.schName = schName;
	}

	public String getSchInfo() {
		return schInfo;
	}

	public void setSchInfo(String schInfo) {
		this.schInfo = schInfo;
	}

	public Date getSchGrantDate() {
		return schGrantDate;
	}

	public void setSchGrantDate(Date schGrantDate) {
		this.schGrantDate = schGrantDate;
	}

	public Date getSchStartDate() {
		return schStartDate;
	}

	public void setSchStartDate(Date schStartDate) {
		this.schStartDate = schStartDate;
	}

	public Date getSchEndDate() {
		return schEndDate;
	}

	public void setSchEndDate(Date schEndDate) {
		this.schEndDate = schEndDate;
	}

	public Employee getSchGranter() {
		return schGranter;
	}

	public void setSchGranter(Employee schGranter) {
		this.schGranter = schGranter;
	}

	public Employee getSchGrantee() {
		return schGrantee;
	}

	public void setSchGrantee(Employee schGrantee) {
		this.schGrantee = schGrantee;
	}

	public Schedule(Integer schId, String schName, String schInfo, Date schGrantDate, Date schStartDate,
			Date schEndDate, Employee schGranter, Employee schGrantee) {
		super();
		this.schId = schId;
		this.schName = schName;
		this.schInfo = schInfo;
		this.schGrantDate = schGrantDate;
		this.schStartDate = schStartDate;
		this.schEndDate = schEndDate;
		this.schGranter = schGranter;
		this.schGrantee = schGrantee;
	}

	public Schedule() {
		super();
	}

	public Schedule(String schName, String schInfo, Date schGrantDate, Date schStartDate, Date schEndDate,
			Employee schGranter, Employee schGrantee) {
		super();
		this.schName = schName;
		this.schInfo = schInfo;
		this.schGrantDate = schGrantDate;
		this.schStartDate = schStartDate;
		this.schEndDate = schEndDate;
		this.schGranter = schGranter;
		this.schGrantee = schGrantee;
	}

	@Override
	public String toString() {
		return "Schedule [schId=" + schId + ", schName=" + schName + ", schInfo=" + schInfo + ", schGrantDate="
				+ schGrantDate + ", schStartDate=" + schStartDate + ", schEndDate=" + schEndDate + ", schGranter="
				+ schGranter + ", schGrantee=" + schGrantee + "]";
	}
    
    


}