package com.icss.oa.car.pojo;

/*
 * 车辆实体类
 * @author bhl
 */
public class Vehicle {

	private Integer vehicleId;
	private String vehicleName;
	private String vehicleLicense;
	private Integer vehicleState;
	private String vehiclePhoto;
	
	public Vehicle() {
		super();
	}
	
	public Vehicle(Integer vehicleId, String vehicleName, String vehicleLicense, Integer vehicleState,
			String vehiclePhoto) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleName = vehicleName;
		this.vehicleLicense = vehicleLicense;
		this.vehicleState = vehicleState;
		this.vehiclePhoto = vehiclePhoto;
	}

	public Vehicle(String vehicleName, String vehicleLicense, Integer vehicleState, String vehiclePhoto) {
		super();
		this.vehicleName = vehicleName;
		this.vehicleLicense = vehicleLicense;
		this.vehicleState = vehicleState;
		this.vehiclePhoto = vehiclePhoto;
	}
	
	public Integer getVehicleId() {
		return vehicleId;
	}
	
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public String getVehicleName() {
		return vehicleName;
	}
	
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	public String getVehicleLicense() {
		return vehicleLicense;
	}
	
	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}
	
	public Integer getVehicleState() {
		return vehicleState;
	}
	
	public void setVehicleState(Integer vehicleState) {
		this.vehicleState = vehicleState;
	}
	
	public String getVehiclePhoto() {
		return vehiclePhoto;
	}
	
	public void setVehiclePhoto(String vehiclePhoto) {
		this.vehiclePhoto = vehiclePhoto;
	}
	
}
