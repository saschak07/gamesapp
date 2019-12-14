package com.gameapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.gameapp.dto.GamesDetailsDto;
import com.gameapp.entity.GamesEntity;
import com.gameapp.exception.NoDetailsFoundException;
import com.gameapp.repository.GamesRepository;

@RunWith(MockitoJUnitRunner.class)
public class GamesDaoTest {
	
	@Mock
	private GamesRepository gamesRepo;
	
	@InjectMocks
	private GamesDao gamesDao = new GamesDaoImpl();
	
	@Test
	public void test_getGamesByName_happyPath() {
		List<GamesEntity> gameList = new ArrayList<GamesEntity>();
		GamesEntity game = new GamesEntity();
		gameList.add(game);
		Mockito.when(gamesRepo.findByTitle(Mockito.anyString())).thenReturn(gameList);
		gamesDao.getGamesByName("name");
		Mockito.verify(gamesRepo).findByTitle(Mockito.anyString());
		
	}
	@Test(expected = NoDetailsFoundException.class)
	public void test_getGamesByName_NodetailsFound() {
		List<GamesEntity> gameList = new ArrayList<GamesEntity>();
		Mockito.when(gamesRepo.findByTitle(Mockito.anyString())).thenReturn(gameList);
		gamesDao.getGamesByName("name");
		Mockito.verify(gamesRepo).findByTitle(Mockito.anyString());
		
	}
	
	@Test
	public void test_updateGame_happyPath() {
		GamesDetailsDto gameDto = new GamesDetailsDto();
		gameDto.setId("1");
		Mockito.when(gamesRepo.findById(Mockito.anyString())).thenReturn(Optional.of(new GamesEntity()));
		gamesDao.updateGame(gameDto);
		Mockito.verify(gamesRepo).findById(Mockito.anyString());
		
	}
	
	@Test(expected = NoDetailsFoundException.class)
	public void test_updateGame_NodetailsFound() {
		GamesDetailsDto gameDto = new GamesDetailsDto();
		gameDto.setId("1");
		Mockito.when(gamesRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
		gamesDao.updateGame(gameDto);
		Mockito.verify(gamesRepo).findById(Mockito.anyString());
		
	}

}
