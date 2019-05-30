package com.icss.oa.common;


public class Pager {

	private int recordCount; //总记录数
	
	private int pageSize = 10; //每页记录数
	
	private int pageCount; //总页数
	
	private int pageNum; //当前页码
	
	private int start; //当前页起始记录下标
	
	public Pager(int recordCount, int pageSize, int pageNum){
		
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		
		
		this.pageCount = this.recordCount / this.pageSize;
		if (this.recordCount % this.pageSize != 0){
			this.pageCount ++;
		}
		
		if (this.pageNum > this.pageCount){
			this.pageNum = this.pageCount;
		}
		if (this.pageNum < 1){
			this.pageNum = 1;
		}
		
		
		
		this.start = (this.pageNum - 1 ) * this.pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	
}
