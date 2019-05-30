package com.icss.oa.card.dao;

import java.util.List;

import com.icss.oa.card.pojo.Team;

public interface TeamMapper {

	void insert(String teamName);

	void delete(Integer teamId);
	
	void update(Team team);
	
	Team queryById(Integer teamId);
	
	List<Team> query();
}