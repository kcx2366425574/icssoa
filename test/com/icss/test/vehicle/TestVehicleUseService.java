package com.icss.test.vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.vehicle.pojo.Vehicle;
import com.icss.oa.vehicle.pojo.VehicleUse;
import com.icss.oa.vehicle.service.VehicleUseService;

public class TestVehicleUseService {

	// 获得Spring容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象(dao对象）
	VehicleUseService service = context.getBean(VehicleUseService.class);

	// 日期转换
	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		return date;
	}

	@Test
	public void queryByCondition() {
			
		Pager pager = new Pager ();
		pager.setStart(0);
		pager.setPageSize(5);
		List<VehicleUse> vehicleUses = service.queryVehByCondition(null, null, null, null,
				pager);
		for (VehicleUse vehicleUse : vehicleUses) {
			System.out.println(vehicleUse);
		}
	}

	@Test
	// 增加用车记录
	public void testInsertVeh() throws ParseException {

		Employee vehUseEmp = new Employee();
		vehUseEmp.setEmpId(1);

		Employee vehUseApprover = new Employee();
		vehUseApprover.setEmpId(3);

		Vehicle veh = new Vehicle();
		veh.setVehicleId(5);

		VehicleUse vehUse = new VehicleUse(vehUseEmp, inform("2019-2-13 14:20:22"), 
				inform("2019-2-14 14:20:22"), "春游bbb", vehUseApprover, "审批通过",veh);
		
		service.insertVeh(vehUse);

	}
	
	@Test
	//修改用车记录
	public void testUpdateVeh() throws ParseException {
		
		Employee vehUseEmp = new Employee();
		vehUseEmp.setEmpId(1);

		Employee vehUseApprover = new Employee();
		vehUseApprover.setEmpId(3);

		Vehicle veh = new Vehicle();
		veh.setVehicleId(5);
		
		VehicleUse vehUse = new VehicleUse(27,vehUseEmp, inform("2019-2-13 14:20:22"), 
				inform("2019-2-14 14:20:22"), "aaa春游", vehUseApprover, "aaaa审批通过",veh);
		
		service.updateVeh(vehUse);
	}
	
	@Test
	//测试模糊查询总记录数
	public void testGetVehCountByCondition() {
		
		int count = service.getVehCountByCondition(1, 2, null, null);
		System.out.println(count);
		
	}
	
	@Test
	//测试模糊查询
	public void testQueryVehByCondition() {
		Pager pager = new Pager(service.getVehCountByCondition(null, null, "审批通过", null), 5, 1);
		List<VehicleUse> list = service.queryVehByCondition(null, null, "审批通过", null, pager);
		for (VehicleUse vehicleUse : list) {
			System.out.println(vehicleUse);
		}
	}
	
	@Test
	//根据id查询用车记录
	public void testQueryVehById() {
		VehicleUse vehUse = service.queryVehById(18);
		System.out.println(vehUse);
	}
}
