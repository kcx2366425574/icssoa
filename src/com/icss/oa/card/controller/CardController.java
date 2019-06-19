package com.icss.oa.card.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;
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
	 * 批量删除名片
	 * 
	 * @param request
	 * @param response
	 * @param card
	 */
	@RequestMapping("/card/deleteMany")
	public void deleteMany(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		service.deleteManyCard(ids);
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
	 * 全文检索
	 * @return 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@RequestMapping("card/queryByIndex")
	@ResponseBody
	public List<Card> queryByIndex(HttpServletRequest request,HttpServletResponse response,String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {
		HttpSession session = request.getSession();
		String empLoginName0 = (String) session.getAttribute("empLoginName");
		
		ArrayList<Integer> ids0 = service.getTeamIds(empLoginName0);
		int size = ids0.size();
		Integer[] ids = (Integer[])ids0.toArray(new Integer[size]);
		List<Card> list0 = service.queryCardByEmp(ids);
		List<Card> list = service.queryCardByIndex(queryStr);
		
		List<Integer> cardId0 = list0.stream().map(Card::getCardId).collect(Collectors.toList());
		List<Integer> cardId = list.stream().map(Card::getCardId).collect(Collectors.toList());
		cardId.retainAll(cardId0);
		
		List<Card> result = new ArrayList<Card>();
		for (Card card : list) {
			boolean isContains = cardId.contains(card.getCardId());
			if (isContains) {
				result.add(card);
			}
		}
		return result;	
	}
	
	/**
	 * 根据登录名条件查询
	 */
	@RequestMapping("card/queryByCond")
	@ResponseBody
	public HashMap<String, Object> queryByCond(HttpServletRequest request, HttpServletResponse response, String empLoginName, Integer pageSize,
			Integer pageNum, Integer teamId, String cardName, String cardSex, String cardIntro) {
		HttpSession session = request.getSession();
		String empLoginName0 = (String) session.getAttribute("empLoginName");
		
		ArrayList<Integer> ids0 = service.getTeamIds(empLoginName0);
		int size = ids0.size();
		
		if (size == 0) {
			Integer[] ids = {-1};
			if (pageNum == null)
				pageNum = 1;
			
			if (pageSize == null)
				pageSize = 10;
			
			Pager pager = new Pager(service.getCardCountByCond(ids, teamId, cardName, cardSex, cardIntro),
					pageSize, pageNum);
			List<Card> list = service.queryCardByCond(ids, pager, teamId, cardName, cardSex, cardIntro);

			// 在Map集合中存储分页数据和名片数据
			HashMap<String, Object> map = new HashMap<>();
			map.put("pager", pager);
			map.put("list", list);

			return map;
		}
		
		Integer[] ids = (Integer[])ids0.toArray(new Integer[size]);
	
		//System.out.println(ids);

		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = 10;
		
		Pager pager = new Pager(service.getCardCountByCond(ids, teamId, cardName, cardSex, cardIntro),
				pageSize, pageNum);
		List<Card> list = service.queryCardByCond(ids, pager, teamId, cardName, cardSex, cardIntro);

		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
}
