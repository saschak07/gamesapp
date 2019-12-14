package com.gameapp.dao;

import java.util.List;
import java.util.Optional;

import com.gameapp.dto.GamesDetailsDto;
import com.gameapp.entity.GamesEntity;

public interface GamesDao {

	List<GamesEntity> getAllGames();

	Optional<GamesEntity> getGameById(String id);

	List<GamesEntity> getGamesByName(String id);

	void save(GamesEntity gamesEntityFromDto);

	Optional<GamesEntity> updateGame(GamesDetailsDto gameDto);

	void removeGame(String id);

}
