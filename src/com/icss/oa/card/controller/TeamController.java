package com.icss.oa.card.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.card.pojo.Team;
import com.icss.oa.card.service.TeamService;
import com.icss.oa.system.pojo.Employee;

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
	 */
	@RequestMapping("/team/add")
	public void add(HttpServletRequest request,HttpServletResponse response, String teamName) {
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		Employee employee = new Employee();
		employee.setEmpId(service.getEmpIdByLname(empLoginName));
		Team team = new Team(teamName, employee);
		
		service.addTeam(team);
	}
	
	/**
	 * 查询全部分组
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/team/query")
	@ResponseBody
	public List<Team> query(HttpServletRequest request,HttpServletResponse response, Integer empId) {
		
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		int id = service.getEmpIdByLname(empLoginName);
		
		return service.queryTeam(id);
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
	public void update(HttpServletRequest request,HttpServletResponse response,Integer teamId, String teamName) {
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		Employee employee = new Employee();
		employee.setEmpId(service.getEmpIdByLname(empLoginName));
		Team team = new Team(teamId, teamName, employee);
		
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
