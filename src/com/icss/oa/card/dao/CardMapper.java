package com.icss.oa.card.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.card.pojo.Card;

public interface CardMapper {

	void insert(Card card);

	void delete(Integer cardId);

	void update(Card card);
	
	Card queryById(Integer cardId);

	List<Card> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("teamId") Integer teamId, @Param("cardName") String cardName, @Param("cardSex") String cardSex,
			@Param("cardIntro") String cardIntro);

	void upTeamNum(Integer teamId);

	void lowTeamNum(Integer teamId);

	int queryTeamIdById(Integer cardId);

	int getCountByCondition(@Param("teamId") Integer teamId, @Param("cardName") String cardName,
			@Param("cardSex") String cardSex, @Param("cardIntro") String cardIntro);

}