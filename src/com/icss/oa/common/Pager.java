package com.icss.oa.common;

/*
 * 共同的分页功能
 */
public class Pager {

	private int recordCount; //总记录数
	
	private int pageSize = 10; //每页多少条
	
	private int pageCount; //共几页
	
	private int pageNum; //当前页码
	
	private int start; //当前记录页起始下标
	
	public Pager(int recordCount, int pageSize, int pageNum){
		
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		
		//计算共几页
		this.pageCount = this.recordCount / this.pageSize;
		if (this.recordCount % this.pageSize != 0){
			this.pageCount ++;
		}
		
		//计算页码
		if (this.pageNum < 1){
			this.pageNum = 1;
		}
		
		if (this.pageNum > this.pageCount){
			this.pageNum = this.pageCount;
		}
		
		//当前起始下标
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
	
	public static void main(String[] args){
		
		Pager pager = new Pager(6, 2, 1);
		System.out.println("pageCount" + pager.getPageCount());
		System.out.println(pager.getStart());
	}
	
}
