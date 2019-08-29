package org.taru.lanqiao.vo;

import java.util.List;

public class PageResult {
	private List list;
	private int pageNum;
	private int pageSize;
	private int total;
	private int pages;
	
	
	public List getList() {
		return list;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotal() {
		return total;
	}
	public int getPages() {
		return pages;
	}
	public void setList(List list) {
		this.list = list;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
}
