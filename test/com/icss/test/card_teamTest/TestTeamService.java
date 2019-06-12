package com.icss.test.card_teamTest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.card.pojo.Team;
import com.icss.oa.card.service.TeamService;
import com.icss.oa.system.pojo.Employee;

/**
 * 分组业务测试类
 * @author Administrator
 *
 */
public class TestTeamService {

	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Service对象
	TeamService service = context.getBean(TeamService.class);
	
	//增加数据
	@Test
	public void testAddTeam() {
		Employee employee = new Employee();
		employee.setEmpId(service.getEmpIdByLname("zhangsan"));
		Team team = new Team("sss", employee);
		service.addTeam(team);
	}
	
	//删除数据
	@Test
	public void testDeleteTeam() {
		service.deleteTeam(16);
	}
	
	//修改数据
	@Test
	public void testUpdateTeam() {
		Employee employee = new Employee();
		employee.setEmpId(service.getEmpIdByLname("zhangsan"));
		Team team = new Team(16, "qqqq", employee);
		service.updateTeam(team);
	}
	
	//通过id查询数据
	@Test
	public void testQueryTeamById() {
		Team team = service.queryTeamById(2);
		System.out.println(team);
	}
	
	//查询全部数据
	@Test
	public void testQueryTeam() {
		List<Team> list = service.queryTeam(2);
		for (Team team : list) {
			System.out.println(team);
		}
	}
	
	//通过登录名获取员工id
	@Test
	public void testGetEmpIdByLname() {
		int id = service.getEmpIdByLname("zhangsan");
		System.out.println(id);
	}

}
