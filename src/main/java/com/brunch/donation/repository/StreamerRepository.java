package com.brunch.donation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.brunch.donation.model.Streamer;

public interface StreamerRepository extends MongoRepository<Streamer, String>{
	
	@Query("{name: '?0'}")
	Streamer findStreamerByName(String name);
	
//	@Query(value="{name: '?0}'", fields="{'name' : '?0'}")
//	List<Streamer> findAll(String name);

}