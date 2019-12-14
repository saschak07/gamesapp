package com.gameapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GamesDetailsDto {

	private String id ;
	private String title;
	private String platform;
	private Double score;
	private String genre;
	private String editors_choice;


}
