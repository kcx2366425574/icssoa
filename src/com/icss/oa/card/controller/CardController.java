package com.icss.oa.card.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.card.pojo.Card;
import com.icss.oa.card.service.CardService;
import com.icss.oa.common.Pager;

/**
 * 名片控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class CardController {

	@Autowired
	private CardService service;

	/**
	 * 增加名片
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/card/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Card card) {
		service.addCard(card);
	}

	/**
	 * 删除名片
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/card/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer cardId) {
		service.deleteCard(cardId);
	}

	/**
	 * 修改名片
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/card/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Integer cardId, Card card) {
		service.updateCard(cardId, card);
	}
	
	/**
	 * 通过id查询名片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/card/get")
	@ResponseBody
	public Card queryById(HttpServletRequest request,HttpServletResponse response,Integer cardId) {
		return service.queryCardById(cardId);
	}

	/**
	 * 条件查询
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/card/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request, HttpServletResponse response, Integer pageSize,
			Integer pageNum, Integer teamId, String cardName, String cardSex, String cardIntro) {
		// return service.queryCardByCondition(page, teamName, cardName, cardSex, cardIntro);

		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;
		
		Pager pager = new Pager(service.getCardCountByCondition(teamId, cardName, cardSex, cardIntro),
				pageSize, pageNum);
		List<Card> list = service.queryCardByCondition(pager, teamId, cardName, cardSex, cardIntro);

		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	/**
	 * 全文检索员工
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@RequestMapping("card/queryByIndex")
	@ResponseBody
	public List<Card> queryByIndex(HttpServletRequest request,HttpServletResponse response,String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {
		
		return service.queryCardByIndex(queryStr);		
	}

}
