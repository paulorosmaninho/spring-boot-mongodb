package com.nelioalves.springbootmongodb.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nelioalves.springbootmongodb.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	//Pesquisa os Posts por titulo ignorando maiúsculas e minusculas 
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	
	//Pesquisa com @Query. Montagem da query:
	//{ <field>: { $regex: /pattern/, $options: '<options>' } }
	//{ 'title' <campo que será pesquisado>: { $regex: ?0 = <primeiro parametro do construtor>, $options: 'i' = <ignore case> } }
	@Query(value = "{ 'title': { $regex: ?0, $options: 'i' } }" )
	List<Post> searchTitle(String text);
}
