package com.nelioalves.springbootmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.springbootmongodb.entities.User;
import com.nelioalves.springbootmongodb.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> findAll(){
		
		return userRepository.findAll();
		
	}
	
}
