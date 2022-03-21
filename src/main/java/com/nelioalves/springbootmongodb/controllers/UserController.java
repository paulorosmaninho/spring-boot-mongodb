package com.nelioalves.springbootmongodb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.springbootmongodb.dto.UserDTO;
import com.nelioalves.springbootmongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {

		List<UserDTO> listUser = userService.findAll();

		return ResponseEntity.ok().body(listUser);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		UserDTO userDTO = userService.findById(id);
		
		return ResponseEntity.ok().body(userDTO);
	}

}
