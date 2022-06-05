package com.example.demo.model;

public class Pagination {
	
	private int page;
	private int size;
	public Pagination(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public int getSize() {
		return size;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	

}
