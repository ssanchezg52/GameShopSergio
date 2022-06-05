package com.example.demo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
	
	public Page<Game> findByplataforms_nameAndNameStartingWith(Pageable pageable, String name, String search);

	
}
