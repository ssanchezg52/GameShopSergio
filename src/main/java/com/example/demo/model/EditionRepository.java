package com.example.demo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EditionRepository extends PagingAndSortingRepository<Edition, Long> {

	public Page<Edition> findByName(Pageable pageable,String name);
	public Page<Edition> findBygame_nameStartingWith(Pageable pageable,String name);
	public Edition findBygame_nameEquals(String name);
	
}
