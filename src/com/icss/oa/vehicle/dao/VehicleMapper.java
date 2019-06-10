package com.icss.oa.vehicle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.vehicle.pojo.Vehicle;

public interface VehicleMapper {

	void insert(Vehicle vehicle);

	void delete(Integer vehicleId);

	void update(Vehicle vehicle);

	Vehicle queryById(Integer vehicleId);

	List<Vehicle> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("vehicleName") String vehicleName, @Param("vehicleLicense") String vehicleLicense,
			@Param("vehicleState") String vehicleState);

	int getCountByCondition(@Param("vehicleName") String vehicleName, @Param("vehicleLicense") String vehicleLicense,
			@Param("vehicleState") String vehicleState);

}