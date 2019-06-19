package com.icss.test.vehicleTest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.vehicle.dao.VehicleMapper;
import com.icss.oa.vehicle.pojo.Vehicle;

public class TestVehicleMapper {

	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	VehicleMapper mapper = context.getBean(VehicleMapper.class);

	// 增加数据
	@Test
	public void testInsert() {
		Vehicle vehicle = new Vehicle("16号车", "辽A-A00016", "空闲");
		mapper.insert(vehicle);
	}

	// 删除数据
	@Test
	public void testDelete() {
		mapper.delete(15);
	}

	// 修改数据
	@Test
	public void testUpdate() {
		Vehicle vehicle = new Vehicle(16, "sfdsg", "啊啊啊啊", "已使用");
		mapper.update(vehicle);
	}

	// 通过id查询
	@Test
	public void testQueryById() {
		Vehicle vehicle = mapper.queryById(16);
		System.out.println(vehicle);
	}

	// 条件获取记录数
	@Test
	public void testGetCountByCondition() {
		int count = mapper.getCountByCondition(null, null, "空闲");
		System.out.println(count);
	}

	// 条件查询(分页、分组、姓名、性别、介绍)
	@Test
	public void testQueryByCondition() {
		List<Vehicle> list = mapper.queryByCondition(0, 3, null, null, "已使用");
		for (Vehicle vehicle : list) {
			System.out.println(vehicle);
		}
	}

}
