package com.icss.test;

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
		String[] names = {"qqq", "www"};
		for (String name : names) {
			int id = mapper.queryTeamIdByName(name);
			mapper.delete(name);
			mapper.lowTeamNum(id);
		}
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
	
	//条件获取记录数
	@Test
	public void testGetCountByCondition() {
		int count = mapper.getCountByCondition(0, 5, null, null, "男", "Java");
		System.out.println(count);
	}

	// 条件查询(分页、分组名称、姓名、性别、介绍)
	@Test
	public void testQueryByCondition() {
		List<Card> list = mapper.queryByCondition(0, 5, "同事", null, null, null);
		for (Card card : list) {
			System.out.println(card);
		}
	}

}
