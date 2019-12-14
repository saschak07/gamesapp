package com.gameapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameapp.dto.GamesDetailsDto;
import com.gameapp.entity.GamesEntity;
import com.gameapp.exception.NoDetailsFoundException;
import com.gameapp.repository.GamesRepository;

@Service
public class GamesDaoImpl implements GamesDao{

	@Autowired
	private GamesRepository gamesRepo;
	
	@Override
	public List<GamesEntity> getAllGames() {
		List<GamesEntity> gamesList = new ArrayList<GamesEntity>();
		gamesRepo.findAll().forEach(game -> gamesList.add(game));
		return gamesList;
	}

	@Override
	public Optional<GamesEntity> getGameById(String id) {
		Optional<GamesEntity> optionalGameDetails =  gamesRepo.findById(id);
		if(!optionalGameDetails.isPresent()) {
			throw new NoDetailsFoundException("no Game found with this Id");
		}
		return optionalGameDetails;
	}

	@Override
	public List<GamesEntity> getGamesByName(String name) {
		List<GamesEntity> gamesResult = gamesRepo.findByTitle(name);
		if(gamesResult.isEmpty()) {
			throw new NoDetailsFoundException("no Game found with this Id");
		}
		
		return gamesResult;
	}

	@Override
	public void save(GamesEntity gamesEntityFromDto) {
		gamesRepo.save(gamesEntityFromDto);
		
	}

	@Override
	@Transactional
	public Optional<GamesEntity> updateGame(GamesDetailsDto gameDto) {
		Optional<GamesEntity> optionalGame = getGameById(gameDto.getId());
		
		if(!optionalGame.isPresent()) {
			throw new NoDetailsFoundException("no game found for this details!!"); 
		}
		
		GamesEntity game = optionalGame.get();
		game.setEditors_choice(gameDto.getEditors_choice());
		game.setGenre(gameDto.getGenre());
		game.setPlatform(game.getPlatform());
		game.setScore(gameDto.getScore());
		game.setTitle(gameDto.getTitle());
		return optionalGame;
	}

	@Override
	public void removeGame(String id) {
		gamesRepo.deleteById(id);
		
	}

}
