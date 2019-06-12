package com.icss.oa.card.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.card.dao.TeamMapper;
import com.icss.oa.card.pojo.Team;

/**
 * 分组服务类
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TeamService {
	
	@Autowired
	private TeamMapper mapper;
	
	//增加数据
	public void addTeam(Team team) { 
		mapper.insert(team);
	}
	
	//删除数据
	public void deleteTeam(Integer teamId) {
		mapper.delete(teamId);
	}
	
	//修改数据
	public void updateTeam(Team team) {
		mapper.update(team);
	}
	
	//通过id查询数据
	@Transactional(readOnly = true)
	public Team queryTeamById(Integer teamId) {
		return mapper.queryById(teamId);
	}
	
	//查询全部数据
	@Transactional(readOnly = true)
	public List<Team> queryTeam(Integer empId) {
		return mapper.query(empId);
	}
	
	//通过登录名获取员工id
	@Transactional(readOnly = true)
	public int getEmpIdByLname(String empLoginName) {
		return mapper.getEmpId(empLoginName);
	}

}
