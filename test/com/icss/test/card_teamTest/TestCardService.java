package com.icss.test.card_teamTest;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.card.index.CardIndexDao;
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
	
	CardIndexDao indexDao = context.getBean(CardIndexDao.class);
	
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
	public void testDeleteCard() {;
		service.deleteCard(44);
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
	
	/**
	 * 重建名片的索引
	 */
	@Test
	public void testCreateIndex(){
		
		Pager pager = new Pager(service.getCardCountByCondition(null, null, null, null),service.getCardCountByCondition(null, null, null, null) ,1);
		List<Card> list = service.queryCardByCondition(pager, null, null, null, null);
		
		for (Card card : list) {
						
			System.out.println(card);
			
			try {
				/********** 生成索引 *************/
				// 创建索引文档
				Document document = new Document();
				document.add(new TextField("cardId", String.valueOf(card.getCardId()), Store.YES));
				document.add(new TextField("cardName", card.getCardName(), Store.YES));
				document.add(new TextField("cardSex", card.getCardSex(), Store.YES));
				document.add(new TextField("cardPhone", card.getCardPhone(), Store.YES));
				document.add(new TextField("cardCareer", card.getCardCareer(), Store.YES));
				document.add(new TextField("cardAddress", card.getCardAddress(), Store.YES));
				document.add(new TextField("cardIntro", card.getCardIntro(), Store.YES));

				// 调用索引dao
				indexDao.create(document);
				
				System.out.println("索引已创建");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	
		
	}
	
	
	/**
	 * 测试全文检索
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@Test
	public void testQueryByIndex() throws ParseException, IOException, InvalidTokenOffsetsException {
		
		List<Card> list = service.queryCardByIndex("曹操林黛玉大战李飞");
		
		for (Card card : list) {
			System.out.println(card);
		}	
		
	}

}
