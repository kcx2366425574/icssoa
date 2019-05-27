package com.icss.oa.card.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.card.pojo.Card;

public interface CardMapper {
	
	void insert(Card card);
	
	void delete(String cardName);
	
	void update(Card card);
	
	int getCount();
	
	List<Card> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize, 
			@Param("teamName") String teamName, @Param("cardName") String cardName, 
			@Param("cardSex") String cardSex, @Param("cardIntro") String cardIntro);
	
	void upTeamNum(Integer teamId);
	
	void lowTeamNum(Integer teamId);
	
	int queryTeamIdByName(String cardName);
	
	int queryTeamIdById(Integer cardId);
	
	int getCountByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize, 
			@Param("teamName") String teamName, @Param("cardName") String cardName, 
			@Param("cardSex") String cardSex, @Param("cardIntro") String cardIntro);
	
}