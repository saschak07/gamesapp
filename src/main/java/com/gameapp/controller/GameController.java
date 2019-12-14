package com.gameapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.gameapp.dao.GamesDao;
import com.gameapp.dto.GamesDetailsDto;
import com.gameapp.utils.conversionUtil;


@RestController
@RequestMapping("/gameapps")
public class GameController {
	
	@Autowired
	private GamesDao gamesDao;
	
	@Autowired
	private conversionUtil util;

	@GetMapping("/games")
	ResponseEntity<List<GamesDetailsDto>> getAllGames(){
		return ResponseEntity.ok(util.getGamesListFromGamesEntities(gamesDao.getAllGames()));
	}
	
	@GetMapping("/search/{name}")
	ResponseEntity<List<GamesDetailsDto>> searchGamesByName(@PathVariable String name){
		return ResponseEntity.ok(util.getGamesListFromGamesEntities(gamesDao.getGamesByName(name)));
	}
	
	@PostMapping("/games")
	ResponseEntity<Object> saveGame(@RequestBody GamesDetailsDto gameDto){
		gamesDao.save(util.getGamesEntityFromDto(gameDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@PutMapping("/games")
	ResponseEntity<GamesDetailsDto> updateGames(@RequestBody GamesDetailsDto gameDto){
		return ResponseEntity.ok(util.getGamesDtoFromEntity(gamesDao.updateGame(gameDto).get()));
	}
	
	@DeleteMapping("/games/{id}")
	ResponseEntity<Object> deleteGame(@PathVariable String id){
		gamesDao.removeGame(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	}
