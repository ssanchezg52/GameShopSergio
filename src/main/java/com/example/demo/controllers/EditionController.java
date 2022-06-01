package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pagination;
import com.example.demo.model.response.Response;
import com.example.demo.services.EditionService;

@RestController
public class EditionController {
	
	@Autowired
	private EditionService editionService;
	
	@GetMapping("getGameEditionListByEdition/{page}/{size}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response> getGameEditionListByEdition(@PathVariable int page,@PathVariable int size){
		ResponseEntity<Response> ok = ResponseEntity.ok(Response.builder()
				.TimeStamp(LocalDateTime.now())
				.Status(HttpStatus.OK)
				.Message("lista de juegos obtenida")
				.Data(Map.of("gameEditionListContent",this.editionService.getGameEditionsListByStandard(new Pagination(page, size)))).build());
		return ok;
	}
	
	@GetMapping("getGameEditionListBySearch/{page}/{size}/{search}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_GUEST')")
	public ResponseEntity<Response> getGameEditionListBySearch(@PathVariable int page,@PathVariable int size,@PathVariable String search){
		ResponseEntity<Response> ok = ResponseEntity.ok(Response.builder()
				.TimeStamp(LocalDateTime.now())
				.Status(HttpStatus.OK)
				.Message("lista de juegos obtenida")
				.Data(Map.of("gameEditionListContent",this.editionService.getGameEditionListBySearch(search, new Pagination(page, size)))).build());
		return ok;
	}
}
