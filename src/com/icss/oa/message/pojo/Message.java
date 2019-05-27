package com.icss.oa.message.pojo;

import java.util.Date;

import com.icss.oa.system.pojo.Employee;

/**
 * 在线信息实体类
 * @author bhl
 */

public class Message {

	private Integer mesId;
	private String mesTitle;
	//private Integer mesSender;
	//private Integer mesReciver;
	private String mesSendConfirm;
	private String mesReadConfirm;
	private Date mesSendDate;
	private String mesInfo;
	private Employee mesSender;
	private Employee mesReciver;
	
	public Message() {
		super();
	}

	public Message(String mesTitle, String mesSendConfirm, String mesReadConfirm, Date mesSendDate, String mesInfo,
			Employee mesSender, Employee mesReciver) {
		super();
		this.mesTitle = mesTitle;
		this.mesSendConfirm = mesSendConfirm;
		this.mesReadConfirm = mesReadConfirm;
		this.mesSendDate = mesSendDate;
		this.mesInfo = mesInfo;
		this.mesSender = mesSender;
		this.mesReciver = mesReciver;
	}

	public Message(Integer mesId, String mesTitle, String mesSendConfirm, String mesReadConfirm, Date mesSendDate,
			String mesInfo, Employee mesSender, Employee mesReciver) {
		super();
		this.mesId = mesId;
		this.mesTitle = mesTitle;
		this.mesSendConfirm = mesSendConfirm;
		this.mesReadConfirm = mesReadConfirm;
		this.mesSendDate = mesSendDate;
		this.mesInfo = mesInfo;
		this.mesSender = mesSender;
		this.mesReciver = mesReciver;
	}

	public Integer getMesId() {
		return mesId;
	}

	public void setMesId(Integer mesId) {
		this.mesId = mesId;
	}

	public String getMesTitle() {
		return mesTitle;
	}

	public void setMesTitle(String mesTitle) {
		this.mesTitle = mesTitle;
	}

	public String getMesSendConfirm() {
		return mesSendConfirm;
	}

	public void setMesSendConfirm(String mesSendConfirm) {
		this.mesSendConfirm = mesSendConfirm;
	}

	public String getMesReadConfirm() {
		return mesReadConfirm;
	}

	public void setMesReadConfirm(String mesReadConfirm) {
		this.mesReadConfirm = mesReadConfirm;
	}

	public Date getMesSendDate() {
		return mesSendDate;
	}

	public void setMesSendDate(Date mesSendDate) {
		this.mesSendDate = mesSendDate;
	}

	public String getMesInfo() {
		return mesInfo;
	}

	public void setMesInfo(String mesInfo) {
		this.mesInfo = mesInfo;
	}

	public Employee getMesSender() {
		return mesSender;
	}

	public void setMesSender(Employee mesSender) {
		this.mesSender = mesSender;
	}

	public Employee getMesReciver() {
		return mesReciver;
	}

	public void setMesReciver(Employee mesReciver) {
		this.mesReciver = mesReciver;
	}

	@Override
	public String toString() {
		return "Message [mesId=" + mesId + ", mesTitle=" + mesTitle + ", mesSendConfirm=" + mesSendConfirm
				+ ", mesReadConfirm=" + mesReadConfirm + ", mesSendDate=" + mesSendDate + ", mesInfo=" + mesInfo
				+ ", mesSender=" + mesSender + ", mesReciver=" + mesReciver + "]";
	}

}
