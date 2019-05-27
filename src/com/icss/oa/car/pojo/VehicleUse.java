package com.icss.oa.car.pojo;

import java.sql.Date;
/*
 * 车辆申请实体类
 * @author bhl
 */

public class VehicleUse {

	private Integer vehicleUseId;
	private Integer vehicleUseEmployee;
	private Date vehicleUseStartTime;
	private Date vehicleUseEndTime;
	private String vehicleUseReason;
	private Integer vehicleUseApprover;
	private Integer vehicleUseApprovalStatus;
	
	public VehicleUse() {
		super();
	}
	
	public VehicleUse(Integer vehicleUseId, Integer vehicleUseEmployee, Date vehicleUseStartTime,
			Date vehicleUseEndTime, String vehicleUseReason, Integer vehicleUseApprover,
			Integer vehicleUseApprovalStatus) {
		super();
		this.vehicleUseId = vehicleUseId;
		this.vehicleUseEmployee = vehicleUseEmployee;
		this.vehicleUseStartTime = vehicleUseStartTime;
		this.vehicleUseEndTime = vehicleUseEndTime;
		this.vehicleUseReason = vehicleUseReason;
		this.vehicleUseApprover = vehicleUseApprover;
		this.vehicleUseApprovalStatus = vehicleUseApprovalStatus;
	}
	
	public VehicleUse(Integer vehicleUseEmployee, Date vehicleUseStartTime, Date vehicleUseEndTime,
			String vehicleUseReason, Integer vehicleUseApprover, Integer vehicleUseApprovalStatus) {
		super();
		this.vehicleUseEmployee = vehicleUseEmployee;
		this.vehicleUseStartTime = vehicleUseStartTime;
		this.vehicleUseEndTime = vehicleUseEndTime;
		this.vehicleUseReason = vehicleUseReason;
		this.vehicleUseApprover = vehicleUseApprover;
		this.vehicleUseApprovalStatus = vehicleUseApprovalStatus;
	}

	public Integer getVehicleUseId() {
		return vehicleUseId;
	}

	public void setVehicleUseId(Integer vehicleUseId) {
		this.vehicleUseId = vehicleUseId;
	}

	public Integer getVehicleUseEmployee() {
		return vehicleUseEmployee;
	}

	public void setVehicleUseEmployee(Integer vehicleUseEmployee) {
		this.vehicleUseEmployee = vehicleUseEmployee;
	}

	public Date getVehicleUseStartTime() {
		return vehicleUseStartTime;
	}

	public void setVehicleUseStartTime(Date vehicleUseStartTime) {
		this.vehicleUseStartTime = vehicleUseStartTime;
	}

	public Date getVehicleUseEndTime() {
		return vehicleUseEndTime;
	}

	public void setVehicleUseEndTime(Date vehicleUseEndTime) {
		this.vehicleUseEndTime = vehicleUseEndTime;
	}

	public String getVehicleUseReason() {
		return vehicleUseReason;
	}

	public void setVehicleUseReason(String vehicleUseReason) {
		this.vehicleUseReason = vehicleUseReason;
	}

	public Integer getVehicleUseApprover() {
		return vehicleUseApprover;
	}

	public void setVehicleUseApprover(Integer vehicleUseApprover) {
		this.vehicleUseApprover = vehicleUseApprover;
	}

	public Integer getVehicleUseApprovalStatus() {
		return vehicleUseApprovalStatus;
	}

	public void setVehicleUseApprovalStatus(Integer vehicleUseApprovalStatus) {
		this.vehicleUseApprovalStatus = vehicleUseApprovalStatus;
	}
	
	
}
