package com.icss.oa.stock.pojo;

import java.sql.Date;


public class Stock {
    private Integer stockId;

    private String stockName;

    private Integer stockNum;

    private Integer stockUsedNum;

    private Integer stockBrokenNum;

//  @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date stockTime;

	public Stock() {
		super();
	}

	public Stock(Integer stockId, String stockName, Integer stockNum, Integer stockUsedNum, Integer stockBrokenNum,
			Date stockTime) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.stockNum = stockNum;
		this.stockUsedNum = stockUsedNum;
		this.stockBrokenNum = stockBrokenNum;
		this.stockTime = stockTime;
	}

	public Stock(String stockName, Integer stockNum, Integer stockUsedNum, Integer stockBrokenNum, Date stockTime) {
		super();
		this.stockName = stockName;
		this.stockNum = stockNum;
		this.stockUsedNum = stockUsedNum;
		this.stockBrokenNum = stockBrokenNum;
		this.stockTime = stockTime;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Integer getStockUsedNum() {
		return stockUsedNum;
	}

	public void setStockUsedNum(Integer stockUsedNum) {
		this.stockUsedNum = stockUsedNum;
	}

	public Integer getStockBrokenNum() {
		return stockBrokenNum;
	}

	public void setStockBrokenNum(Integer stockBrokenNum) {
		this.stockBrokenNum = stockBrokenNum;
	}

	public Date getStockTime() {
		return stockTime;
	}

	public void setStockTime(Date stockTime) {
		this.stockTime = stockTime;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", stockName=" + stockName + ", stockNum=" + stockNum + ", stockUsedNum="
				+ stockUsedNum + ", stockBrokenNum=" + stockBrokenNum + ", stockTime=" + stockTime + "]";
	}
   
}