package com.icss.oa.vehicle.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.vehicle.dao.VehicleMapper;
import com.icss.oa.vehicle.pojo.Vehicle;

@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleService {

	@Autowired
	private VehicleMapper mapper;

	// 增加数据
	public void addVehicle(Vehicle vehicle) {
		mapper.insert(vehicle);
	}

	// 删除数据
	public void deleteVehicle(Integer vehicleId) {
		mapper.delete(vehicleId);
	}

	// 修改数据
	public void updateVehicle(Vehicle vehicle) {
		mapper.update(vehicle);
	}

	// 通过id查询数据
	@Transactional(readOnly = true)
	public Vehicle queryVehicleById(Integer vehicleId) {
		return mapper.queryById(vehicleId);
	}

	// 条件获取记录数
	public Integer getVehicleCountByCondition(String vehicleName, String vehicleLicense, String vehicleState) {
		return mapper.getCountByCondition(vehicleName, vehicleLicense, vehicleState);
	}

	//条件查询(分页、分组名称、姓名、性别、介绍)
	@Transactional(readOnly = true)
	public List<Vehicle> queryVehicleByCondition(Pager page, String vehicleName, String vehicleLicense, String vehicleState) {
		return mapper.queryByCondition(page.getStart(), page.getPageSize(), vehicleName, vehicleLicense, vehicleState);
	}

}
