package com.nelioalves.springbootmongodb.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nelioalves.springbootmongodb.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	//Pesquisa os Posts por titulo ignorando maiúsculas e minusculas 
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	
	//Pesquisa por título com @Query. Montagem da query:
	//{ <field>: { $regex: ,  $options: '<options>' } }
	//{ 'title' <campo que será pesquisado>: { $regex: ?0 = <primeiro parametro do construtor>, $options: 'i' = <ignore case> } }
	@Query(value = "{ 'title': { $regex: ?0, $options: 'i' } }" )
	List<Post> searchTitle(String text);
	
	//Pesquisa com vários campos com @Query. Montagem da query:
	//{ $and: [ { <field>: { $regex: <condição e parâmetro (vindo do construtor)>} }, { <field>: { $regex: <condição e parâmetro (vindo do construtor)>} } , ... , { $or: [ { <expression1> }, { <expression2> }, ... , { <expressionN> } ] } ] }
	@Query(value = "{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
	
}
