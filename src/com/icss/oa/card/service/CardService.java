package com.icss.oa.card.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.card.dao.CardMapper;
import com.icss.oa.card.pojo.Card;
import com.icss.oa.common.Pager;

/**
 * 名片服务类
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CardService {
	
	@Autowired
	private CardMapper mapper;
	
	//增加数据
	public void addCard(Card card) {
		mapper.insert(card);
		mapper.upTeamNum(card.getTeam().getTeamId());
	}
	
	//删除数据
	public void deleteCard(Integer[] ids) {
		for (Integer id : ids) {
			int id0 = mapper.queryTeamIdById(id);
			mapper.delete(id);
			mapper.lowTeamNum(id0);
		}
	}
		
	//修改数据
	public void updateCard(Integer cardId, Card card) {
		int id0 = mapper.queryTeamIdById(cardId);
		int id = card.getTeam().getTeamId();
		mapper.update(card);
		mapper.upTeamNum(id);
		mapper.lowTeamNum(id0);
	}
	
	//通过id查询数据
	@Transactional(readOnly = true)
	public Card queryCardById(Integer cardId) {
		return mapper.queryById(cardId);
	}
	
	//条件获取记录数
	public Integer getCardCountByCondition(Integer teamId, String cardName, String cardSex, String cardIntro) {
		return mapper.getCountByCondition(teamId, cardName, cardSex, cardIntro);
	}
	
	//条件查询(分页、分组名称、姓名、性别、介绍)
	@Transactional(readOnly = true)
	public List<Card> queryCardByCondition(Pager page, Integer teamId, String cardName, String cardSex, String cardIntro) {
		return mapper.queryByCondition(page.getStart(), page.getPageSize(), teamId, cardName, cardSex, cardIntro);
	}

}
