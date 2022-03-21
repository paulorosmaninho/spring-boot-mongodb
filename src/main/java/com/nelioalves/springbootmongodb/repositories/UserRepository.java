package com.nelioalves.springbootmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.springbootmongodb.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
