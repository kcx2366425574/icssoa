package com.icss.oa.vehicle.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.vehicle.pojo.VehicleUse;

public interface VehicleUseMapper {
	
	void insert(VehicleUse vehUse);
	
	void update(VehicleUse vehUse);
	
	List<VehicleUse> queryByCondition(@Param("vehUseEmp") Integer vehUseEmp,
			@Param("vehAppEmp") Integer vehAppEmp, @Param("vehAppState") String vehAppState,
			@Param("vehicleId") Integer vehicleId,@Param("start") Integer start,
			@Param("pageSize") Integer pageSize);
	
	void delete(Integer vehUseId);
	
	int getCountByCondition(@Param("vehUseEmp") Integer vehUseEmp,
			@Param("vehAppEmp") Integer vehAppEmp, @Param("vehAppState") String vehAppState,
			@Param("vehicleId") Integer vehicleId);
	
	VehicleUse queryById(Integer vehUseId);
}
