package com.icss.oa.folder.pojo;

import java.sql.Date;

import com.icss.oa.system.pojo.Employee;

public class Folder {
    private Integer folId;

    private String folName;

    private Double folSize;

    private Integer folSuperFol;

    private String folDescription;

    private String folState;

    private Date folUpdateDate;

    private Date folCreateDate;

    private Employee folEmp;
    

	public Folder(Integer folId) {
		super();
		this.folId = folId;
	}

	public Integer getFolId() {
		return folId;
	}

	public void setFolId(Integer folId) {
		this.folId = folId;
	}

	public String getFolName() {
		return folName;
	}

	public void setFolName(String folName) {
		this.folName = folName;
	}

	public Double getFolSize() {
		return folSize;
	}

	public void setFolSize(Double folSize) {
		this.folSize = folSize;
	}

	public Integer getFolSuperFol() {
		return folSuperFol;
	}

	public void setFolSuperFol(Integer folSuperFol) {
		this.folSuperFol = folSuperFol;
	}

	public String getFolDescription() {
		return folDescription;
	}

	public void setFolDescription(String folDescription) {
		this.folDescription = folDescription;
	}

	public String getFolState() {
		return folState;
	}

	public void setFolState(String folState) {
		this.folState = folState;
	}

	public Date getFolUpdateDate() {
		return folUpdateDate;
	}

	public void setFolUpdateDate(Date folUpdateDate) {
		this.folUpdateDate = folUpdateDate;
	}

	public Date getFolCreateDate() {
		return folCreateDate;
	}

	public void setFolCreateDate(Date folCreateDate) {
		this.folCreateDate = folCreateDate;
	}

	public Employee getFolEmp() {
		return folEmp;
	}

	public void setFolEmp(Employee folEmp) {
		this.folEmp = folEmp;
	}

	public Folder() {
		super();
	}

	public Folder(Integer folId, String folName, Double folSize, Integer folSuperFol, String folDescription,
			String folState, Date folUpdateDate, Date folCreateDate, Employee folEmp) {
		super();
		this.folId = folId;
		this.folName = folName;
		this.folSize = folSize;
		this.folSuperFol = folSuperFol;
		this.folDescription = folDescription;
		this.folState = folState;
		this.folUpdateDate = folUpdateDate;
		this.folCreateDate = folCreateDate;
		this.folEmp = folEmp;
	}

	public Folder(String folName, Double folSize, Integer folSuperFol, String folDescription, String folState,
			Date folUpdateDate, Date folCreateDate, Employee folEmp) {
		super();
		this.folName = folName;
		this.folSize = folSize;
		this.folSuperFol = folSuperFol;
		this.folDescription = folDescription;
		this.folState = folState;
		this.folUpdateDate = folUpdateDate;
		this.folCreateDate = folCreateDate;
		this.folEmp = folEmp;
	}

	@Override
	public String toString() {
		return "Folder [folId=" + folId + ", folName=" + folName + ", folSize=" + folSize + ", folSuperFol="
				+ folSuperFol + ", folDescription=" + folDescription + ", folState=" + folState + ", folUpdateDate="
				+ folUpdateDate + ", folCreateDate=" + folCreateDate + ", folEmp=" + folEmp + "]";
	}
   
}