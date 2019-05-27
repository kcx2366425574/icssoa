package com.icss.oa.plan.pojo;

import java.util.Date;

import com.icss.oa.system.pojo.Department;

public class Plan {
    private Integer planId;

    private String planName;

    private Date planTime;

    private String planInfo;

    private Department dept;
    
    

    public Plan() {
		super();
	}
    

	public Plan(String planName, Date planTime, String planInfo, Department dept) {
		super();
		this.planName = planName;
		this.planTime = planTime;
		this.planInfo = planInfo;
		this.dept = dept;
	}

	

	public Plan(Integer planId, String planName, Date planTime, String planInfo, Department dept) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.planTime = planTime;
		this.planInfo = planInfo;
		this.dept = dept;
	}


	public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public String getPlanInfo() {
        return planInfo;
    }

    public void setPlanInfo(String planInfo) {
        this.planInfo = planInfo == null ? null : planInfo.trim();
    }

    public Department getDeptId() {
        return dept;
    }

    public void setDeptId(Department deptId) {
        this.dept = deptId;
    }


	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", planName=" + planName + ", planTime=" + planTime + ", planInfo=" + planInfo
				+ ", dept=" + dept + "]";
	}
    
}