package com.icss.oa.card.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.card.pojo.Card;

public interface CardMapper {
	
	int getLastInsertId();

	void insert(Card card);

	void delete(Integer cardId);
	
	//批量删除
	void deleteMany(Integer[] ids);

	void update(Card card);
	
	Card queryById(Integer cardId);

	void upTeamNum(Integer teamId);

	void lowTeamNum(Integer teamId);

	int queryTeamIdById(Integer cardId);
	
	List<Card> queryByEmp(@Param("ids") Integer[] ids);
	
	ArrayList<Integer> getIds(String empLoginName);
	
	List<Card> queryByCond(@Param("ids") Integer[] ids, @Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("teamId") Integer teamId, @Param("cardName") String cardName, @Param("cardSex") String cardSex,
			@Param("cardIntro") String cardIntro);
	
	int getCountByCond(@Param("ids") Integer[] ids, @Param("teamId") Integer teamId, @Param("cardName") String cardName,
			@Param("cardSex") String cardSex, @Param("cardIntro") String cardIntro);

}