package com.icss.test.vehicle;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.common.Pager;
import com.icss.oa.vehicle.pojo.Vehicle;
import com.icss.oa.vehicle.service.VehicleService;

public class TestVehicleService {

	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Service对象
	VehicleService service = context.getBean(VehicleService.class);

	// 增加数据
	@Test
	public void testAddVehicle() {
		Vehicle vehicle = new Vehicle("2222", "阿斯蒂芬", "已使用");
		service.addVehicle(vehicle);
	}
	
	//删除数据
	@Test
	public void testDeleteVehicle() {
		service.deleteVehicle(17);
	}
	
	//修改数据
	@Test
	public void testUpdateVehicle() {
		Vehicle vehicle = new Vehicle(16, "oooo", "红薯淀粉", "空闲");
		service.updateVehicle(vehicle);
	}
	
	//通过id查询数据
	@Test
	public void testQueryVehicleById() {
		Vehicle vehicle = service.queryVehicleById(16);
		System.out.println(vehicle);
	}
	
	//条件获取记录数
	@Test
	public void testGetVehicleCountByCondition() {
		int count = service.getVehicleCountByCondition(null, null, "空闲");
		System.out.println(count);
	}
	
	//条件查询 (分页)
	@Test
	public void testQueryVehicleByCondition() {
		Pager page = new Pager(service.getVehicleCountByCondition(null, null, "空闲"), 3, 0);
		List<Vehicle> list = service.queryVehicleByCondition(page, null, null, "空闲");
		for(Vehicle vehicle : list) {
			System.out.println(vehicle);
		}
	}

}
