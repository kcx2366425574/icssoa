package com.icss.oa.vehicle.pojo;

public class Vehicle {
    private Integer vehicleId;

    private String vehicleName;

    private String vehicleLicense;

    private String vehicleState;
 
    public Vehicle() {
		super();
	}

	public Vehicle(Integer vehicleId, String vehicleName, String vehicleLicense, String vehicleState) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleName = vehicleName;
		this.vehicleLicense = vehicleLicense;
		this.vehicleState = vehicleState;
	}

	public Vehicle(String vehicleName, String vehicleLicense, String vehicleState) {
		super();
		this.vehicleName = vehicleName;
		this.vehicleLicense = vehicleLicense;
		this.vehicleState = vehicleState;
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
        this.vehicleName = vehicleName == null ? null : vehicleName.trim();
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense == null ? null : vehicleLicense.trim();
    }

    public String getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(String vehicleState) {
        this.vehicleState = vehicleState == null ? null : vehicleState.trim();
    }

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleName=" + vehicleName + ", vehicleLicense=" + vehicleLicense
				+ ", vehicleState=" + vehicleState + "]";
	}
    
}