package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.card.pojo.Team;
import com.icss.oa.card.service.TeamService;

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
		service.addTeam("ppp");
	}
	
	//删除数据
	@Test
	public void testDeleteTeam() {
		service.deleteTeam(11);
	}
	
	//修改数据
	@Test
	public void testUpdateTeam() {
		Team team = new Team(7, "aaa");
		service.updateTeam(team);
	}
	
	//通过name查询数据
	@Test
	public void testQueryTeamById() {
		Team team = service.queryTeamById(2);
		System.out.println(team);
	}
	
	//查询全部数据
	@Test
	public void testQueryTeam() {
		List<Team> list = service.queryTeam();
		for (Team team : list) {
			System.out.println(team);
		}
	}

}
