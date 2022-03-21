package com.nelioalves.springbootmongodb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.springbootmongodb.dto.UserDTO;
import com.nelioalves.springbootmongodb.entities.User;
import com.nelioalves.springbootmongodb.repositories.UserRepository;
import com.nelioalves.springbootmongodb.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<UserDTO> findAll() {

		List<User> listEntity = userRepository.findAll();

		List<UserDTO> listUserDTO = new ArrayList<>();

		listEntity.forEach(entityElement -> listUserDTO.add(new UserDTO(entityElement)));

		return listUserDTO;
	}
	
	public UserDTO findById(String id) {
		
		Optional<User> objOptional = userRepository.findById(id);
		
		User entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("Usuário " + id + " não encontrado"));
		
		return new UserDTO(entity);
	}

}
