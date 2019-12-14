package com.gameapp.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
public class GamesEntity {

	@Id
	private String id = UUID.randomUUID().toString();
	private String title;
	private String platform;
	private Double score;
	private String genre;
	private String editors_choice;
}
