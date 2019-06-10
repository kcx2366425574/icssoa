package com.icss.test.vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.vehicle.dao.VehicleUseMapper;
import com.icss.oa.vehicle.pojo.Vehicle;
import com.icss.oa.vehicle.pojo.VehicleUse;

/**
 * 用车记录测试类
 * 
 * @author Administrator
 *
 */
public class TestVehicleUseMapper {
	
	// 获得Spring容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	// 获得Mapper对象(dao对象）
	VehicleUseMapper mapper = context.getBean(VehicleUseMapper.class);

	// 日期转换
	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		return date;
	}

	@Test
	// 增加用车记录
	public void testInsert() throws ParseException {

		Employee vehUseEmp = new Employee();
		vehUseEmp.setEmpId(1);

		Employee vehUseApprover = new Employee();
		vehUseApprover.setEmpId(1);

		Vehicle veh = new Vehicle();
		veh.setVehicleId(5);

		VehicleUse vehUse = new VehicleUse(vehUseEmp, inform("2019-2-13 14:20:22"), inform("2019-2-14 14:20:22"), "aaa车", vehUseApprover,
				"审批通过", veh);
		
		mapper.insert(vehUse);
		
	}
	
	@Test
	//修改用车记录
	public void testUpdate() throws ParseException {
		
		Employee vehUseEmp = new Employee();
		vehUseEmp.setEmpId(1);

		Employee vehUseApprover = new Employee();
		vehUseApprover.setEmpId(2);

		Vehicle veh = new Vehicle();
		veh.setVehicleId(1);
		
		VehicleUse vehUse = new VehicleUse(19,vehUseEmp, inform("2019-2-13 14:20:22"), inform("2019-2-14 14:20:22"), "aaa车", vehUseApprover,
				"审批通过", veh);
		
		mapper.update(vehUse);
	}
	
	@Test
	//模糊查询用车记录
	public void testQueryByCondition() {
		
		List<VehicleUse> list = mapper.queryByCondition(null, null, null, 10,0,5);
		
		for (VehicleUse vehUse : list) {
			System.out.println(vehUse);
		}
	}
	
	@Test
	//模糊查询的总记录数
	public void testGetCountByCondition() {
		int count = mapper.getCountByCondition(null, null, null, 1);
		System.out.println(count);
	}
	
	@Test
	//根据id查询用车记录
	public void testQueryById() {
		VehicleUse vehUse = mapper.queryById(18);
		System.out.println(vehUse);
	}
}
