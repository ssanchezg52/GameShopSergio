package com.example.demo.model;

import org.springframework.data.domain.Page;

public class EditionDTO {
	
	private Page<Edition> editionList;
	private Boolean lastPage;
	
	public EditionDTO(Page<Edition> editionList, Boolean lastPage) {
		super();
		this.editionList = editionList;
		this.lastPage = lastPage;
	}

	public Page<Edition> getEditionList() {
		return editionList;
	}

	public Boolean getLastPage() {
		return lastPage;
	}
}
