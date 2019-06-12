package com.icss.test.card_teamTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.card.dao.CardMapper;
import com.icss.oa.card.pojo.Card;
import com.icss.oa.card.pojo.Team;

public class TestCardMapper {

	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Mapper对象（dao对象）
	CardMapper mapper = context.getBean(CardMapper.class);


	// 增加数据
	@Test
	public void testInsert() {

		Team team = new Team();
		team.setTeamId(4);

		Card card = new Card("aaa", "xx", "13564802359", "艺术家", "天津", "hhh@icss.com", "无", team);
		mapper.insert(card);
		mapper.upTeamNum(4);
	}

	// 删除数据
	@Test
	public void testDelete() {
		Integer[] ids = {74, 75};
		for (Integer id : ids) {
			int id0 = mapper.queryTeamIdById(id);
			mapper.delete(id);
			mapper.lowTeamNum(id0);
		}
	}
	
	//批量删除
	@Test
	public void testDeleteMany() {
		Integer[] ids = {76, 77};
		for (Integer id : ids) {
			Integer id0 = mapper.queryTeamIdById(id);
			mapper.lowTeamNum(id0);
		}
		mapper.deleteMany(ids);
	}

	// 修改数据
	@Test
	public void testUpdate() {
		Team team = new Team();
		team.setTeamId(7);
		
		int id0 = mapper.queryTeamIdById(23);
		Card card = new Card(23, "AAAA", "EE", "13564802359", "艺术家", "天津", "hhh@icss.com", "无", team);
		int id = card.getTeam().getTeamId();
		mapper.update(card);
		mapper.upTeamNum(id);
		mapper.lowTeamNum(id0);
	}
	
	//通过id查询
	@Test
	public void testQueryById() {
		Card card = mapper.queryById(1);
		System.out.println(card);
	}
	
	//根据员工登陆查询
	@Test
	public void testQueryByEmp() {
		Integer[] ids = {1, 2, 3, 4};
		List<Card> list = mapper.queryByEmp(ids);
		for (Card card : list) {
			System.out.println(card);
		}
		
	}
	
	//根据员工登录名查询teamId
	@Test
	public void testGetIds() {
		ArrayList<Integer> ids = mapper.getIds("zhangsan");
		System.out.println(ids);
	}
	
	//登录后条件查询
	@Test
	public void testQueryByCond() {
		Integer[] ids = {1, 4};
		List<Card> list = mapper.queryByCond(ids, 0, 10, null, "刘", null, null);
		for (Card card : list) {
			System.out.println(card);
		}
	}
	
	//登录后条件获取记录数
	@Test
	public void testGetCountByCond() {
		Integer[] ids = {1, 4};
		int count = mapper.getCountByCond(ids, null, "周", null, null);
		System.out.println(count);
	}
			
}
