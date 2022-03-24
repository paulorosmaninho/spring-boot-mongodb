package com.nelioalves.springbootmongodb.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.springbootmongodb.dto.PostDTO;
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
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {

		UserDTO userDTO = userService.findById(id);

		return ResponseEntity.ok().body(userDTO);
	}

	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {

		userDTO = userService.insert(userDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId())
				.toUri();

		return ResponseEntity.created(uri).body(userDTO);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		userService.deleteById(id);

		return ResponseEntity.noContent().build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable String id) {

		userDTO = userService.update(userDTO, id);

		return ResponseEntity.ok().body(userDTO);
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<PostDTO>> findPosts(@PathVariable String id) {

		UserDTO userDTO = userService.findByIdPosts(id);

		return ResponseEntity.ok().body(userDTO.getPostsDto());
	}


}
