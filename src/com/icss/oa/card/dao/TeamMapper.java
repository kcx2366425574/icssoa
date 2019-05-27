package com.icss.oa.card.dao;

import java.util.List;

import com.icss.oa.card.pojo.Team;

public interface TeamMapper {

	void insert(String teamName);

	void delete(String teamName);
	
	void update(Team team);
	
	Team queryByName(String teamName);
	
	List<Team> query();
}