package com.icss.oa.card.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.card.pojo.Team;
import com.icss.oa.card.service.TeamService;

/**
 * 分组控制器
 * @author Administrator
 *
 */
@Controller
public class TeamController {

	@Autowired
	private TeamService service;
	
	/**
	 * 增加分组
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/team/add")
	public void add(HttpServletRequest request,HttpServletResponse response,String teamName) {
		service.addTeam(teamName);
	}
	
	/**
	 * 查询全部分组
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/team/query")
	@ResponseBody
	public List<Team> query(HttpServletRequest request,HttpServletResponse response) {
		return service.queryTeam();
	}
	
	/**
	 * 删除分组
	 * @param request
	 * @param response
	 * @param team
	 */
	@RequestMapping("/team/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer teamId) {
		service.deleteTeam(teamId);
	}
	
	/**
	 * 修改分组
	 * @param request
	 * @param response
	 * @param team
	 */
	@RequestMapping("/team/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Team team) {
		service.updateTeam(team);
	}

	
	/**
	 * 通过id查询分组
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/team/get")
	@ResponseBody
	public Team queryById(HttpServletRequest request,HttpServletResponse response,Integer teamId) {
		return service.queryTeamById(teamId);
	}
	
}
