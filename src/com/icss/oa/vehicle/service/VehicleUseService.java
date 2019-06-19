package com.icss.oa.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.vehicle.dao.VehicleUseMapper;
import com.icss.oa.vehicle.pojo.VehicleUse;

@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleUseService {

	@Autowired
	private VehicleUseMapper mapper;
	
	//增加
	public void insertVeh(VehicleUse vehUse){
		mapper.insert(vehUse);
	}
	

	//修改
	public void updateVeh(VehicleUse vehUse){
		mapper.update(vehUse);
	}
	
	
	//删除
	public void deleteVeh(Integer vehUseId){
		mapper.delete(vehUseId);
	}
	
	
	//查询
	@Transactional(readOnly = true)
	public List<VehicleUse> queryVehByCondition(Integer vehUseEmp, String vehAppState,
			Integer vehicleId,Pager pager){
		return mapper.queryByCondition(vehUseEmp,  vehAppState, vehicleId,pager.getStart(),pager.getPageSize());
	}
	
	//模糊查询的总记录数
	@Transactional(readOnly = true)
	public int getVehCountByCondition(Integer vehUseEmp, String vehAppState,
			Integer vehicleId) {
		return mapper.getCountByCondition(vehUseEmp, vehAppState, vehicleId);
	}
	
	//根据id查询用车记录
	@Transactional(readOnly=true)
	public VehicleUse queryVehById(Integer vehUseId) {
		return mapper.queryById(vehUseId);
	}

}
