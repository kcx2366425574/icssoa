package com.icss.test.card_teamTest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.card.dao.TeamMapper;
import com.icss.oa.card.pojo.Team;
import com.icss.oa.system.pojo.Employee;

public class TestTeamMapper {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	TeamMapper mapper = context.getBean(TeamMapper.class);


	// 增加数据
	@Test
	public void testInsert() {
		Employee employee = new Employee();
		employee.setEmpId(2);
		Team team = new Team("hhh", employee);
		mapper.insert(team);
	}
	
	//删除数据
	@Test
	public void testDelete() {
		mapper.delete(12);
	}
	
	//修改数据
	@Test
	public void testUpdate() {
		Employee employee = new Employee();
		employee.setEmpId(2);
		
		Team team = new Team(15, "www", employee);
		mapper.update(team);
	}
	
	//通过id查询数据
	@Test
	public void testQueryById() {
		Team team = mapper.queryById(2);
		System.out.println(team);
	}
	
	//查询全部数据
	@Test
	public void testQuery() {
		List<Team> list = mapper.query(2);
		for (Team team : list) {
			System.out.println(team);
		}	
	}
	
	//通过登录名获取员工id
	@Test
	public void testGetEmpId() {
		int id = mapper.getEmpId("lisi");
		System.out.println(id);
	}

}
