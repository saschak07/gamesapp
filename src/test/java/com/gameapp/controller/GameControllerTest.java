package com.gameapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameapp.dao.GamesDao;
import com.gameapp.dto.GamesDetailsDto;
import com.gameapp.entity.GamesEntity;
import com.gameapp.exception.NoDetailsFoundException;
import com.gameapp.utils.conversionUtil;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	private MockMvc mock;
	@Mock
	private GamesDao gamesDao;
	
	@Spy
	private conversionUtil util;
	
	@InjectMocks
	private GameController controller;
	private GamesDetailsDto gameDto;
	
	@Before
	public void setUp()
    {
		mock = MockMvcBuilders.standaloneSetup(controller).build();
		gameDto = new GamesDetailsDto();
    }
	
	@Test
	public void test_getAllGames_happyPath() throws Exception {
		List<GamesEntity> gamesList = new ArrayList<GamesEntity>();
		Mockito.when(gamesDao.getAllGames()).thenReturn(gamesList);
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is2xxSuccessful();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gameapps/games");
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        mock.perform(requestBuilder).andExpect(resultMatcher);
        Mockito.verify(gamesDao).getAllGames();
		
	}
	
	@Test
	public void test_SearchName_happyPath() throws Exception {
		List<GamesEntity> gamesList = new ArrayList<GamesEntity>();
		Mockito.when(gamesDao.getGamesByName(Mockito.anyString())).thenReturn(gamesList);
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is2xxSuccessful();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gameapps/search/abcd");
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        mock.perform(requestBuilder).andExpect(resultMatcher);
        Mockito.verify(gamesDao).getGamesByName(Mockito.anyString());
		
	}
	@Test(expected = Exception.class)
	public void test_SearchName_NoDetailsFound() throws Exception {
		List<GamesEntity> gamesList = new ArrayList<GamesEntity>();
		Mockito.when(gamesDao.getGamesByName(Mockito.anyString())).thenThrow(NoDetailsFoundException.class);
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is4xxClientError();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gameapps/search/abcd");
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        mock.perform(requestBuilder).andExpect(resultMatcher);
        Mockito.verify(gamesDao).getGamesByName(Mockito.anyString());
		
	}
	@Test
	public void test_saveGames_happyPath() throws Exception {
		List<GamesEntity> gamesList = new ArrayList<GamesEntity>();
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is2xxSuccessful();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gameapps/games");
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(new ObjectMapper().writeValueAsString(gameDto));
        mock.perform(requestBuilder).andExpect(resultMatcher);
        Mockito.verify(gamesDao).save(Mockito.any());
		
	}
	
	@Test
	public void test_updateGames_happyPath() throws Exception {
		List<GamesEntity> gamesList = new ArrayList<GamesEntity>();
		Mockito.when(gamesDao.updateGame(Mockito.any())).thenReturn(Optional.of(new GamesEntity()));
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is2xxSuccessful();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/gameapps/games");
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(new ObjectMapper().writeValueAsString(gameDto));
        mock.perform(requestBuilder).andExpect(resultMatcher);
        Mockito.verify(gamesDao).updateGame(Mockito.any());
		
	}
	@Test(expected = Exception.class)
	public void test_updateGames_NoDetailsFound() throws Exception {
		List<GamesEntity> gamesList = new ArrayList<GamesEntity>();
		Mockito.when(gamesDao.updateGame(Mockito.any())).thenThrow(NoDetailsFoundException.class);
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is4xxClientError();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/gameapps/games");
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(new ObjectMapper().writeValueAsString(gameDto));
        mock.perform(requestBuilder).andExpect(resultMatcher);
        Mockito.verify(gamesDao).updateGame(Mockito.any());
		
	}
	
	@Test
	public void test_deleteGames_happyPath() throws Exception {
		List<GamesEntity> gamesList = new ArrayList<GamesEntity>();
		ResultMatcher resultMatcher = MockMvcResultMatchers.status().is2xxSuccessful();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/gameapps/games/1");
        requestBuilder.accept(MediaType.APPLICATION_JSON);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        mock.perform(requestBuilder).andExpect(resultMatcher);
        Mockito.verify(gamesDao).removeGame(Mockito.any());
		
	}
}
