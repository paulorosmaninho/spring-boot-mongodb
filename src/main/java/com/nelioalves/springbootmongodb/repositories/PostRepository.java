package com.nelioalves.springbootmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.springbootmongodb.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
