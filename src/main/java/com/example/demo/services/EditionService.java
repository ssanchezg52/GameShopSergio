package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Edition;
import com.example.demo.model.EditionRepository;
import com.example.demo.model.Pagination;
import com.example.demo.model.Plataform;

@Service
public class EditionService {
	
	@Autowired
	private EditionRepository editionRepository;
	
	public Page<Edition> getGameEditionsListByStandard(Pagination pagination) {
		Page<Edition> gameEditionList = this.editionRepository.findByName(PageRequest.of(pagination.getPage(),pagination.getSize()),"Standard");
		List<Edition> content = gameEditionList.getContent().stream().collect(Collectors.toList());
		ArrayList<Edition> editionWithPlataform = new ArrayList<Edition>();
		for (Edition edition : content) {
			ArrayList<Plataform> plataformArray = edition.getPlataformArray();
			edition.setPlataformArray(removeRepeated(plataformArray)); 
			editionWithPlataform.add(edition);
		}
		return new PageImpl<>(editionWithPlataform, PageRequest.of(pagination.getPage(), pagination.getSize()),editionWithPlataform.size());
	}

	private ArrayList<Plataform> removeRepeated(ArrayList<Plataform> plataformArray) {
		plataformArray.sort(new Comparator<Plataform>() {
			public int compare(Plataform p1, Plataform p2) {
				return p1.getName().compareToIgnoreCase(p2.getName());
			};
		});
		for (int i = 0; i < plataformArray.size()-1; i++) {
			Plataform plataform = plataformArray.get(i);
			Plataform plataformNext = plataformArray.get(i+1);
			if (plataform.getUrlIcon().equals(plataformNext.getUrlIcon())) {
				plataformArray.remove(i+1);
				i--;
			}
			
		}
		return plataformArray;
	}

	public Page<Edition> getGameEditionListBySearch(String search, Pagination pagination) {
		return this.editionRepository.findBygame_nameStartingWith(PageRequest.of(pagination.getPage(), pagination.getSize()), search);
	}

}
