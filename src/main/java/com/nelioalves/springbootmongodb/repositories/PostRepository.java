package com.nelioalves.springbootmongodb.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.springbootmongodb.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	//Pesquisa os Posts por titulo ignorando maiúsculas e minusculas 
	List<Post> findByTitleContainingIgnoreCase(String text);
	
}
