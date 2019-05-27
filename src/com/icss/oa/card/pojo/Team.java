package com.icss.oa.card.pojo;

public class Team {
    private Integer teamId;

    private String teamName;

    private Integer teamNum;

    public Team() {
		super();
	}

	public Team(Integer teamId, String teamName, Integer teamNum) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamNum = teamNum;
	}

	public Team(String teamName, Integer teamNum) {
		super();
		this.teamName = teamName;
		this.teamNum = teamNum;
	}

	public Team(String teamName) {
		super();
		this.teamName = teamName;
	}

	public Team(Integer teamId, String teamName) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
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
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public Integer getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
    }

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", teamNum=" + teamNum + "]";
	}
    
}