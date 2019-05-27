package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.card.dao.TeamMapper;
import com.icss.oa.card.pojo.Team;

public class TestTeamMapper {
	
	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	TeamMapper mapper = context.getBean(TeamMapper.class);


	// 增加数据
	@Test
	public void testInsert() {
		mapper.insert("hhh");
	}
	
	//删除数据
	@Test
	public void testDelete() {
		mapper.delete("xxx");
	}
	
	//修改数据
	@Test
	public void testUpdate() {
		Team team = new Team(6, "www");
		mapper.update(team);
	}
	
	//查询数据
	@Test
	public void testQueryByName() {
		Team team = mapper.queryByName("www");
		System.out.println(team);
	}
	
	//查询全部数据
	@Test
	public void testQuery() {
		List<Team> list = mapper.query();
		for (Team team : list) {
			System.out.println(team);
		}	
	}

}
