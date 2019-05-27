package com.icss.oa.card.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public void delete(HttpServletRequest request, HttpServletResponse response, String[] names) {
		service.deleteCard(names);
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
	 * 条件获取记录数
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/card/getCount")
	@ResponseBody
	public Integer getCount(HttpServletRequest request, HttpServletResponse response, Pager page, String teamName,
			String cardName, String cardSex, String cardIntro) {
		return service.getCardCountByCondition(page, teamName, cardName, cardSex, cardIntro);
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
	public List<Card> query(HttpServletRequest request, HttpServletResponse response, Pager page, String teamName,
			String cardName, String cardSex, String cardIntro) {
		return service.queryCardByCondition(page, teamName, cardName, cardSex, cardIntro);
	}
	
}
