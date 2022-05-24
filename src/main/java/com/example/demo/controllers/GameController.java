package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.response.Response;
import com.example.demo.services.GameService;

@RestController
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("getGameList/{page}/{size}")
	public ResponseEntity<Response> getListGameByPage(@PathVariable int page, @PathVariable int size){
		ResponseEntity<Response> ok = ResponseEntity.ok(Response.builder()
				.TimeStamp(LocalDateTime.now())
				.Status(HttpStatus.OK)
				.Message("lista de juegos obtenida")
				.Data(Map.of("gameListContent",this.gameService.getGameList(page, size))).build());
		return ok;
	}
}
