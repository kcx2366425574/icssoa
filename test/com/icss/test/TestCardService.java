package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.card.pojo.Card;
import com.icss.oa.card.pojo.Team;
import com.icss.oa.card.service.CardService;
import com.icss.oa.common.Pager;

/**
 * 名片业务测试类
 * 
 * @author Administrator
 *
 */
public class TestCardService {

	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 获得Service对象
	CardService service = context.getBean(CardService.class);
	
	//增加数据
	@Test
	public void testAddCard() {
		Team team = new Team();
		team.setTeamId(1);

		Card card = new Card("aaa", "xx", "13564802359", "艺术家", "天津", "hhh@icss.com", "无", team);
		service.addCard(card);
	}
	
	//删除数据
	@Test
	public void testDeleteCard() {
		String[] names = {"www", "sss"};
		service.deleteCard(names);
	}
	
	//修改数据
	@Test
	public void testUpdateCard() {
		Team team = new Team();
		team.setTeamId(7);

		Card card = new Card(23, "KKK", "xx", "13564802359", "艺术家", "天津", "hhh@icss.com", "无", team);
		service.updateCard(23, card);
	}
	  
	//条件获取记录数
	@Test
	public void testGetCardCountByCondition() {
		Pager page = new Pager(service.getCardCount(), 5, 0);
		int count = service.getCardCountByCondition(page, null, null, "女", null);
		System.out.println(count);
	}
	
	//条件查询
	@Test
	public void testQueryCardByCondition() {
		Pager page = new Pager(service.getCardCount(), 5, 0);
		List<Card> list = service.queryCardByCondition(page, null, null, null, "Java");
		for(Card card : list) {
			System.out.println(card);
		}
	}

}
