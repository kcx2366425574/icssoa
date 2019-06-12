package com.icss.oa.card.pojo;

import com.icss.oa.system.pojo.Employee;

public class Team {
    private Integer teamId;

    private String teamName;

    private Integer teamNum;
    
    private Employee emp;

	public Team() {
		super();
	}

	public Team(String teamName, Employee emp) {
		super();
		this.teamName = teamName;
		this.emp = emp;
	}

	public Team(Integer teamId, String teamName, Employee emp) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.emp = emp;
	}

	public Team(Integer teamId, String teamName, Integer teamNum, Employee emp) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamNum = teamNum;
		this.emp = emp;
	}

	public Team(String teamName, Integer teamNum, Employee emp) {
		super();
		this.teamName = teamName;
		this.teamNum = teamNum;
		this.emp = emp;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getTeamNum() {
		return teamNum;
	}

	public void setTeamNum(Integer teamNum) {
		this.teamNum = teamNum;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", teamNum=" + teamNum + ", emp=" + emp + "]";
	}

	 
}