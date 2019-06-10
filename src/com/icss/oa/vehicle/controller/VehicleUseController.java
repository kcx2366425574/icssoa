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
import com.icss.oa.vehicle.pojo.VehicleUse;
import com.icss.oa.vehicle.service.VehicleUseService;

@Controller
public class VehicleUseController {

	@Autowired
	private VehicleUseService service;

	@RequestMapping("/veh/add")
	public void add(HttpServletRequest request, HttpServletResponse response, VehicleUse vehUse) {
		service.insertVeh(vehUse);
	}

	@RequestMapping("/veh/update")
	public void update(HttpServletRequest request, HttpServletResponse response, VehicleUse vehUse) {
		service.updateVeh(vehUse);
	}

	@RequestMapping("/veh/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer vehId) {
		service.deleteVeh(vehId);
	}

	@RequestMapping("/veh/queryByCondition")
	@ResponseBody
	public HashMap<String, Object> queryByCondition(HttpServletRequest request, HttpServletResponse response,
			Integer vehUseEmp, Integer vehAppEmp, String vehAppState, Integer vehicleId, Integer pageNum, Integer pageSize) {

		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 5;

		Pager pager = new Pager(service.getVehCountByCondition(vehUseEmp, vehAppEmp, vehAppState, vehicleId), pageSize, pageNum);
		List<VehicleUse> list = service.queryVehByCondition(vehUseEmp, vehAppEmp, vehAppState, vehicleId, pager);
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	@RequestMapping("veh/get")
	@ResponseBody
	public VehicleUse get(HttpServletRequest request,HttpServletResponse response,Integer vehUseId) {
		return service.queryVehById(vehUseId);
	}

}
