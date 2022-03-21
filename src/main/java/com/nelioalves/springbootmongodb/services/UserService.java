package com.nelioalves.springbootmongodb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.springbootmongodb.dto.UserDTO;
import com.nelioalves.springbootmongodb.entities.User;
import com.nelioalves.springbootmongodb.repositories.UserRepository;

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

}
