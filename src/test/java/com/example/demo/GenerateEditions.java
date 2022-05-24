package com.example.demo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Edition;
import com.example.demo.model.EditionRepository;
import com.example.demo.model.Game;
import com.example.demo.model.GameMother;
import com.example.demo.model.GameRepository;
import com.example.demo.model.Plataform;
import com.example.demo.model.PlataformRepository;
import com.example.demo.model.PriceMother;

@SpringBootTest
class GenerateEditions {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private PlataformRepository plataformRepository;
	@Autowired
	private EditionRepository editionRepository;

	@Test
	void contextLoads() {
		Iterable<Plataform> plataforms = plataformRepository.findAll();
		ArrayList<Plataform> plataformsList = new ArrayList<>(); 
		plataforms.forEach(plataformsList::add);
		ArrayList<Game> gameList = GameMother.getGameList(plataformsList);
		ArrayList<Float> prices = PriceMother.getPriceList();
		for (int i = 0; i < gameList.size(); i++) {
			Game game = gameRepository.save(gameList.get(i));
			Edition edition = new Edition("Standard", prices.get(i), game);
			editionRepository.save(edition);
		}
	}
}
