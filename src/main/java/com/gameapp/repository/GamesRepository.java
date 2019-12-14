package com.gameapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gameapp.entity.GamesEntity;

public interface GamesRepository extends CrudRepository<GamesEntity, String>{

	List<GamesEntity> findByTitle(String name);
}
