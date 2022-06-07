package com.example.demo.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Edition;
import com.example.demo.model.EditionDTO;
import com.example.demo.model.EditionRepository;
import com.example.demo.model.Filter;
import com.example.demo.model.Game;
import com.example.demo.model.GameRepository;
import com.example.demo.model.Pagination;
import com.example.demo.model.Plataform;

@Service
public class EditionService {
	
	@Autowired
	private EditionRepository editionRepository;
	@Autowired
	private GameRepository gameRepository;
	
	public EditionDTO getGameEditionsListByStandard(Pagination pagination) {
		Page<Edition> gameEditionList = this.editionRepository.findByName(PageRequest.of(pagination.getPage(),pagination.getSize()),"Standard");
		List<Edition> content = gameEditionList.getContent().stream().collect(Collectors.toList());
		Page<Edition> gameEditionListModify = modifyPageRemovingRepeatedPlataforms(content, pagination);
		boolean lastPage = isLastPage(pagination);
		return new EditionDTO(gameEditionListModify, lastPage);
	}

	private Page<Edition> modifyPageRemovingRepeatedPlataforms(List<Edition> content,Pagination pagination) {
		ArrayList<Edition> editionWithPlataform = new ArrayList<Edition>();
		for (Edition edition : content) {
			ArrayList<Plataform> plataformArray = edition.getPlataformArray();
			edition.setPlataformArray(removeRepeated(plataformArray)); 
			editionWithPlataform.add(edition);
		}
		return new PageImpl<>(editionWithPlataform, PageRequest.of(pagination.getPage(), pagination.getSize()),editionWithPlataform.size());
	}
	
	private ArrayList<Plataform> removeRepeated(ArrayList<Plataform> plataformArray) {
		plataformArray = sortArrayPlataform(plataformArray);
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
	
	private ArrayList<Plataform> sortArrayPlataform(ArrayList<Plataform> plataformArray){
		plataformArray.sort(new Comparator<Plataform>() {
			public int compare(Plataform p1, Plataform p2) {
				return p1.getName().compareToIgnoreCase(p2.getName());
			};
		});
		return plataformArray;
	}

	private boolean isLastPage(Pagination pagination) {
		Page<Edition> gameEditionListNextPage = this.editionRepository.findByName(PageRequest.of(pagination.getPage() + 1,pagination.getSize()),"Standard");
		return isLast(gameEditionListNextPage);
	}

	public EditionDTO getGameEditionListByFilter(Filter filter, Pagination pagination) {
		Page<Edition> gameEditionListByFilter = this.editionRepository.findBygame_nameStartingWith(PageRequest.of(pagination.getPage(), pagination.getSize()), filter.getSearch());
		if (!filter.getPlataform().equals("Todos")) {
			gameEditionListByFilter = getEditionListWithFilters(pagination,filter,false);
			boolean lastPage = isLastPageWithFilter(pagination,filter);
			return new EditionDTO(gameEditionListByFilter, lastPage);
		}
		boolean lastPage = isLastPageWithSearch(pagination,filter.getSearch());
		return new EditionDTO(gameEditionListByFilter, lastPage);
	}
	
	private Page<Edition> getEditionListWithFilters(Pagination pagination, Filter filter, boolean checkLast){
		Page<Game> gamesPlataform;
		if (!checkLast) {
			gamesPlataform = gameRepository.findByplataforms_nameAndNameStartingWith(PageRequest.of(pagination.getPage(), pagination.getSize()), filter.getPlataform(),filter.getSearch());
		}else {
			gamesPlataform = gameRepository.findByplataforms_nameAndNameStartingWith(PageRequest.of(pagination.getPage() + 1, pagination.getSize()), filter.getPlataform(),filter.getSearch());
		}
		List<Game> collect = gamesPlataform.getContent().stream().collect(Collectors.toList());
		ArrayList<Edition> editionListWithFilter = new ArrayList<>();
		for (Game game : collect) {
			editionListWithFilter.add(editionRepository.findBygame_nameEquals(game.getName()));
		}
		return modifyPageRemovingRepeatedPlataforms(editionListWithFilter,pagination);
	}

	private boolean isLastPageWithSearch(Pagination pagination,String search) {
		Page<Edition> gameEditionListNextPage = this.editionRepository.findBygame_nameStartingWith(PageRequest.of(pagination.getPage() + 1, pagination.getSize()), search);
		return isLast(gameEditionListNextPage);
	}
	
	private boolean isLastPageWithFilter(Pagination pagination,Filter filter) {
		Page<Edition> gameEditionListNextPage = getEditionListWithFilters(pagination, filter, true);
		return isLast(gameEditionListNextPage);
	}

	private boolean isLast(Page<Edition> gameEditionListNextPage) {
		if (gameEditionListNextPage.getContent().size() == 0) {
			return true;
		} 
		return false;
	}

}
