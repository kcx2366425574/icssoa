package com.icss.oa.analyse.dto;

/**
 * 数据传输对象（映射特定查询结果）
 * @author Administrator
 *
 */
public class StockCount {
	
	private String stockName;

    private Integer stockCount;

	public StockCount() {
		super();
	}

	public StockCount(String stockName, Integer stockCount) {
		super();
		this.stockName = stockName;
		this.stockCount = stockCount;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	@Override
	public String toString() {
		return "StockCount [stockName=" + stockName + ", stockCount=" + stockCount + "]";
	}

}
