package com.icss.test.card_teamTest;

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
		Integer[] ids = {38, 39};
		service.deleteCard(ids);
	}
	
	//修改数据
	@Test
	public void testUpdateCard() {
		Team team = new Team();
		team.setTeamId(7);

		Card card = new Card(23, "KKK", "xx", "13564802359", "艺术家", "天津", "hhh@icss.com", "无", team);
		service.updateCard(23, card);
	}
	
	//通过id查询数据
	@Test
	public void testQueryCardById() {
		Card card = service.queryCardById(1);
		System.out.println(card);
	}
	  
	//条件获取记录数
	@Test
	public void testGetCardCountByCondition() {
		int count = service.getCardCountByCondition(1, null, null, null);
		System.out.println(count);
	}
	
	//条件查询 (分页、分组、姓名、性别、介绍)
	@Test
	public void testQueryCardByCondition() {
		Pager page = new Pager(service.getCardCountByCondition(1, null, null, null), 5, 0);
		List<Card> list = service.queryCardByCondition(page, 1, null, null, null);
		for(Card card : list) {
			System.out.println(card);
		}
	}

}