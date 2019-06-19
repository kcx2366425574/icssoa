package com.icss.oa.vehicle.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.vehicle.pojo.Vehicle;
import com.icss.oa.vehicle.service.VehicleService;

@Controller
public class VehicleController {
	
	@Autowired
	private VehicleService service;
	
	/**
	 * 增加车辆
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/vehicle/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle) {
		service.addVehicle(vehicle);
	}
	
	/**
	 * 删除车辆
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/vehicle/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer vehicleId) {
		service.deleteVehicle(vehicleId);
	}

	/**
	 * 修改车辆
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/vehicle/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle) {
		service.updateVehicle(vehicle);
	}
	
	/**
	 * 通过id查询车辆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vehicle/get")
	@ResponseBody
	public Vehicle queryById(HttpServletRequest request,HttpServletResponse response,Integer vehicleId) {
		return service.queryVehicleById(vehicleId);
	}

	/**
	 * 条件查询
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/vehicle/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request, HttpServletResponse response, Integer pageSize,
			Integer pageNum, String vehicleName, String vehicleLicense, String vehicleState) {

		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = service.getVehicleCountByCondition(vehicleName, vehicleLicense, vehicleState);
		
		Pager pager = new Pager(service.getVehicleCountByCondition(vehicleName, vehicleLicense, vehicleState),
				pageSize, pageNum);
		List<Vehicle> list = service.queryVehicleByCondition(pager, vehicleName, vehicleLicense, vehicleState);

		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

}
