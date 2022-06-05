package com.example.demo.model;

public class Filter {
	
	private String plataform;
	private String search;
	public Filter(String plataform, String search) {
		super();
		this.plataform = plataform;
		this.search = search;
	}
	public String getPlataform() {
		return plataform;
	}
	public void setPlataform(String plataform) {
		this.plataform = plataform;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
}
