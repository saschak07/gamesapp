package com.gameapp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gameapp.dto.GamesDetailsDto;
import com.gameapp.entity.GamesEntity;

@Component
public class conversionUtil extends ModelMapper{

	public GamesDetailsDto getGamesDtoFromEntity(GamesEntity gameEntity) {
		return map(gameEntity, GamesDetailsDto.class);
	}
	
	public List<GamesDetailsDto> getGamesListFromGamesEntities(List<GamesEntity> gamesList){
		List<GamesDetailsDto> gamesListResponse = new ArrayList<GamesDetailsDto>();
		gamesList.forEach(game -> gamesListResponse.add(map(game, GamesDetailsDto.class)));
		return gamesListResponse;
	}

	public GamesEntity getGamesEntityFromDto(GamesDetailsDto gameDto) {
		GamesEntity game =  map(gameDto, GamesEntity.class);
		game.setId(UUID.randomUUID().toString());
		return game;
	}
}
