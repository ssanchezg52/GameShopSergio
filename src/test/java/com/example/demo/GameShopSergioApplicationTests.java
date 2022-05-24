package com.example.demo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Game;
import com.example.demo.model.GameMother;
import com.example.demo.model.GameRepository;
import com.example.demo.model.Plataform;
import com.example.demo.model.PlataformMother;
import com.example.demo.model.PlataformRepository;

@SpringBootTest
class GameShopSergioApplicationTests {
	
	@Autowired
	private PlataformRepository plataformRepository;

	@Test
	void contextLoads() {
		ArrayList<Plataform> plataformList = PlataformMother.getPlataformList();
		for (Plataform plataform : plataformList) {
			plataformRepository.save(plataform);
		}
	}
}
