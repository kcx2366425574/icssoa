package com.icss.oa.vehicle.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.icss.oa.system.pojo.Employee;
import com.icss.oa.vehicle.pojo.Vehicle;
/**
 * 车辆使用记录实体类
 * @author bhl
 *
 */

public class VehicleUse {
	
    private Integer vehUseId;

    private Employee vehUseEmp;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date vehUseStart;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date vehUseEnd;

    private String vehUseReason;

    private Employee vehUseApprover;

    private String vehAppState;
    
    private Vehicle veh;

    public VehicleUse() {
		super();
	}

	public VehicleUse(Integer vehUseId, Employee vehUseEmp, Date vehUseStart, Date vehUseEnd, String vehUseReason,
			Employee vehUseApprover, String vehAppState, Vehicle veh) {
		super();
		this.vehUseId = vehUseId;
		this.vehUseEmp = vehUseEmp;
		this.vehUseStart = vehUseStart;
		this.vehUseEnd = vehUseEnd;
		this.vehUseReason = vehUseReason;
		this.vehUseApprover = vehUseApprover;
		this.vehAppState = vehAppState;
		this.veh = veh;
	}

	public VehicleUse(Employee vehUseEmp, Date vehUseStart, Date vehUseEnd, String vehUseReason, Employee vehUseApprover,
			String vehAppState, Vehicle veh) {
		super();
		this.vehUseEmp = vehUseEmp;
		this.vehUseStart = vehUseStart;
		this.vehUseEnd = vehUseEnd;
		this.vehUseReason = vehUseReason;
		this.vehUseApprover = vehUseApprover;
		this.vehAppState = vehAppState;
		this.veh = veh;
	}

	public Integer getVehUseId() {
		return vehUseId;
	}

	public void setVehUseId(Integer vehUseId) {
		this.vehUseId = vehUseId;
	}

	public Employee getVehUseEmp() {
		return vehUseEmp;
	}

	public void setVehUseEmp(Employee vehUseEmp) {
		this.vehUseEmp = vehUseEmp;
	}

	public Date getVehUseStart() {
		return vehUseStart;
	}

	public void setVehUseStart(Date vehUseStart) {
		this.vehUseStart = vehUseStart;
	}

	public Date getVehUseEnd() {
		return vehUseEnd;
	}

	public void setVehUseEnd(Date vehUseEnd) {
		this.vehUseEnd = vehUseEnd;
	}

	public String getVehUseReason() {
		return vehUseReason;
	}

	public void setVehUseReason(String vehUseReason) {
		this.vehUseReason = vehUseReason;
	}

	public Employee getVehUseApprover() {
		return vehUseApprover;
	}

	public void setVehUseApprover(Employee vehUseApprover) {
		this.vehUseApprover = vehUseApprover;
	}

	public String getVehAppState() {
		return vehAppState;
	}

	public void setVehAppState(String vehAppState) {
		this.vehAppState = vehAppState;
	}

	public Vehicle getVeh() {
		return veh;
	}

	public void setVeh(Vehicle veh) {
		this.veh = veh;
	}

	@Override
	public String toString() {
		return "VehicleUse [vehUseId=" + vehUseId + ", vehUseEmp=" + vehUseEmp + ", vehUseStart=" + vehUseStart
				+ ", vehUseEnd=" + vehUseEnd + ", vehUseReason=" + vehUseReason + ", vehUseApprover=" + vehUseApprover
				+ ", vehAppState=" + vehAppState + ", veh=" + veh + "]";
	}
  
}