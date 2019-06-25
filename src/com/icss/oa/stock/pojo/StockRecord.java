package com.icss.oa.stock.pojo;

import java.sql.Date;

import com.icss.oa.system.pojo.Department;

public class StockRecord {
    private Integer srId;

    private Department dept;

    private Stock stock;

    private Integer srNum;

    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date srTime;

	public StockRecord() {
		super();
	}

	public StockRecord(Integer srId, Department dept, Stock stock, Integer srNum, Date srTime) {
		super();
		this.srId = srId;
		this.dept = dept;
		this.stock = stock;
		this.srNum = srNum;
		this.srTime = srTime;
	}

	public StockRecord(Department dept, Stock stock, Integer srNum, Date srTime) {
		super();
		this.dept = dept;
		this.stock = stock;
		this.srNum = srNum;
		this.srTime = srTime;
	}

	public Integer getSrId() {
		return srId;
	}

	public void setSrId(Integer srId) {
		this.srId = srId;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Integer getSrNum() {
		return srNum;
	}

	public void setSrNum(Integer srNum) {
		this.srNum = srNum;
	}

	public Date getSrTime() {
		return srTime;
	}

	public void setSrTime(Date srTime) {
		this.srTime = srTime;
	}

	@Override
	public String toString() {
		return "StockRecord [srId=" + srId + ", dept=" + dept + ", stock=" + stock + ", srNum=" + srNum + ", srTime="
				+ srTime + "]";
	}
   
}